package com.blas.blascommon.enums;

public enum FileType {

    JPG("jpg"),
    JPEG("jpeg"),
    PNG("png"),
    SVG("svg"),
    GIF("gif"),
    XLSX("xlsx"),
    XLS("xls");

    private String postfix;

    FileType(String postfix) {
        this.postfix = postfix;
    }

    public String getPostfix() {
        return postfix;
    }
}
