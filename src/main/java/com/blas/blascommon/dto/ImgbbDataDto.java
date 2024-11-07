package com.blas.blascommon.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ImgbbDataDto {

  private String id;

  private String title;

  @JsonProperty("url_viewer")
  private String urlViewer;

  private String url;

  @JsonProperty("display_url")
  private String displayUrl;

  private int width;

  private int height;

  private int size;

  private long time;

  private int expiration;

  private ImgbbImageDto image;

  private ImgbbImageDto thumb;

  private ImgbbImageDto medium;

  @JsonProperty("delete_url")
  private String deleteUrl;

}
