package com.github.edipermadi.smartcard;

abstract class CapDecoderImplBase {
    static final String COMPONENT_Header = "Header.cap";
    static final String COMPONENT_Directory = "Directory.cap";
    static final String COMPONENT_Applet = "Applet.cap";
    static final String COMPONENT_Import = "Import.cap";
    static final String COMPONENT_ConstantPool = "ConstantPool.cap";
    static final String COMPONENT_Class = "Class.cap";
    static final String COMPONENT_Method = "Method.cap";
    static final String COMPONENT_StaticField = "StaticField.cap";
    static final String COMPONENT_ReferenceLocation = "RefLocation.cap";
    static final String COMPONENT_Export = "Export.cap";
    static final String COMPONENT_Descriptor = "Descriptor.cap";
    static final String COMPONENT_Debug = "Debug.cap";

    static final int TAG_COMPONENT_Header = 1;
    static final int TAG_COMPONENT_Directory = 2;

    static final int ACC_INT = 0x01;
    static final int ACC_EXPORT = 0x02;
    static final int ACC_APPLET = 0x04;
}
