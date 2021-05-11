package com.woonjin.blog.application.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LogInResponse {

  private String name;
  private String email;

  public static LogInResponse of(String name, String email) {
    return new LogInResponse(name, email);
  }
}
