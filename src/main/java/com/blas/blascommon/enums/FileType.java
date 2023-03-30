package com.blas.blascommon.enums;

public enum FileType {

  JPG("jpg"),
  JPEG("jpeg"),
  PNG("png"),
  SVG("svg"),
  HEIC("heic"),
  RAW("raw"),
  GIF("gif"),
  MP3("mp3"),
  MP4("mp4"),
  MOV("mov"),
  AVI("avi"),
  FLV("flv"),
  DOC("doc"),
  DOCX("docx"),
  XLSX("xlsx"),
  XLS("xls"),
  PPT("ppt"),
  PPTX("pptx"),
  PDF("pdf"),
  CSV("csv"),
  EXE("exe");

  private final String postfix;

  FileType(String postfix) {
    this.postfix = postfix;
  }

  public String getPostfix() {
    return postfix;
  }
}
