package com.woonjin.blog.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SignUpRequest {

  private String email;

  private String password;

  @JsonProperty(value = "password_check")
  private String passwordCheck;

  private String name;
}
