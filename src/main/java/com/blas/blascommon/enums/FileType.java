package com.blas.blascommon.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum FileType {

  JPG("jpg", ".jpg"),
  JPEG("jpeg", ".jpeg"),
  PNG("png", ".png"),
  SVG("svg", ".svg"),
  HEIC("heic", ".heic"),
  RAW("raw", ".raw"),
  RAF("raf", ".raf"),
  GIF("gif", ".gif"),
  MP3("mp3", ".mp3"),
  MP4("mp4", ".mp4"),
  MOV("mov", ".mov"),
  AVI("avi", ".avi"),
  FLV("flv", ".flv"),
  DOC("doc", ".doc"),
  DOCX("docx", ".docx"),
  XLSX("xlsx", ".xlsx"),
  XLS("xls", ".xls"),
  PPT("ppt", ".ppt"),
  PPTX("pptx", ".pptx"),
  PDF("pdf", ".pdf"),
  CSV("csv", ".csv"),
  DAT("dat", ".dat"),
  CONF("conf", ".conf"),
  YAML("yaml", ".yaml"),
  ZIP("zip", ".zip"),
  EXE("exe", ".exe");

  private final String postfix;
  private final String fileExtension;
}
