package com.github.edipermadi.smartcard;

import com.github.edipermadi.smartcard.exc.CapDecodeHeaderException;
import com.github.edipermadi.smartcard.exc.CapException;
import com.github.edipermadi.smartcard.exc.CapFormatException;
import org.apache.commons.io.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * CAP File decoder implementation
 *
 * @author Edi Permadi
 */
public class CapDecoderImpl extends CapDecoderImplBase implements CapDecoder {

    @Override
    public Cap decode(final InputStream stream) throws CapException {
        final ZipInputStream zis = new ZipInputStream(stream);
        try {
            final CapBuilder builder = new CapBuilder();
            while (true) {
                final ZipEntry ze = zis.getNextEntry();
                if (ze == null) {
                    break;
                } else if (ze.isDirectory()) {
                    continue;
                }

                final String path = ze.getName();
                final String name = Paths.get(path).toFile().getName();
                final byte[] payload = IOUtils.toByteArray(zis);
                zis.closeEntry();

                switch (name) {
                    case COMPONENT_Header:
                        builder.setHeader(processCapHeader(payload));
                        break;
                    case COMPONENT_Directory:
                    case COMPONENT_Applet:
                    case COMPONENT_Import:
                    case COMPONENT_ConstantPool:
                    case COMPONENT_Class:
                    case COMPONENT_Method:
                    case COMPONENT_StaticField:
                    case COMPONENT_ReferenceLocation:
                    case COMPONENT_Export:
                    case COMPONENT_Descriptor:
                    case COMPONENT_Debug:
                }
            }

            return builder.build();
        } catch (final IOException ex) {
            throw new CapFormatException("unrecogzied CAP format", ex);
        } finally {
            IOUtils.closeQuietly(zis);
        }
    }

    /**
     * Parse CAP header. The following is the structure of CAP header
     * <pre>
     * header_component {
     *     u1 tag
     *     u2 size
     *     u4 magic
     *     u1 minor_version
     *     u1 major_version
     *     ui flags
     *     package_info package
     *     package_name_info package_name
     * }
     *
     * package_info {
     *     u1 minor_version
     *     u1 major_version
     *     u1 AID_length
     *     u1 AID[AID_length]
     * }
     *
     * package_name_info {
     *     u1 name_length
     *     u1 name[name_length]
     * }
     * </pre>
     *
     * @param payload CAP header payload
     * @return CAP Header object
     * @throws CapDecodeHeaderException when CAP Header decoding failed
     */
    private Cap.Header processCapHeader(byte[] payload) throws CapDecodeHeaderException {
        final CapHeaderBuilder builder = new CapHeaderBuilder();
        final ByteArrayInputStream bais = new ByteArrayInputStream(payload);
        final DataInputStream dis = new DataInputStream(bais);
        try {
            /* parse tag */
            final int tag = dis.readByte();
            if (tag != TAG_COMPONENT_Header) {
                throw CapDecodeHeaderException.invalidTag(tag);
            }

            /* parse size */
            final int size = dis.readShort();
            if ((size + 3) != payload.length) {
                throw CapDecodeHeaderException.invalidLength();
            }

            /* parse magic */
            final int magic = dis.readInt();
            if (magic != 0xdecaffed) {
                throw CapDecodeHeaderException.invalidMagic();
            }

            /* parse version and flags */
            builder.setHeaderVersion(parseVersion(dis))
                    .setHeaderFlags(dis.readByte());

            /* parse package info */
            final int packageInfoVersion = parseVersion(dis);
            final int aidLength = dis.readByte();
            final byte[] aid = new byte[aidLength];
            if (dis.read(aid) != aidLength) {
                throw CapDecodeHeaderException.invalidPackageAID();
            }
            builder.setPackageInfoVersion(packageInfoVersion)
                    .setAid(aid);

            /* optionally set package name info */
            if (bais.available() > 0) {
                final int nameLength = dis.readByte();
                final byte[] name = new byte[nameLength];
                if (dis.read(name) != nameLength) {
                    throw CapDecodeHeaderException.invalidPackageName();
                }

                builder.setPackageInfoName(new String(name, StandardCharsets.UTF_8));
            }

            return builder.build();
        } catch (final IOException ex) {
            throw new CapDecodeHeaderException("failed to parse CAP header", ex);
        }
    }

    private int parseVersion(final DataInputStream dis) throws IOException {
        int version;
        version = dis.readByte() & 0xff;
        version += (dis.readByte() & 0xff) << 8;
        return version;
    }
}
