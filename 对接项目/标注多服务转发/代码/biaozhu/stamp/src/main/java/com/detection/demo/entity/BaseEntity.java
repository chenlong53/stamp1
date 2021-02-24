package com.detection.demo.entity;

import java.io.*;

public class BaseEntity implements Serializable
{
  private static final long serialVersionUID = 1L;
  private String callType;
  private String images;

  public String getCallType() {
    return this.callType;
  }

  public void setCallType(final String callType) {
    this.callType = callType;
  }

  public String getImages() {
    return this.images;
  }

  public void setImages(final String images) {
    this.images = images;
  }
}
