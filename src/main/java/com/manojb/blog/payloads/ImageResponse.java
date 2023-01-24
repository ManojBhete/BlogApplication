package com.manojb.blog.payloads;

import lombok.Data;

@Data
public class ImageResponse {


  private   String filename;
  private   String message;


    public ImageResponse(String filename, String message) {
        this.filename = filename;
        this.message = message;
    }
}
