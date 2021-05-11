package com.woonjin.blog.application.dto;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SignUpResponse {

  private String email;
  private String name;

  public static SignUpResponse of(
      String email,
      String name
  ) {
    return new SignUpResponse(
        email,
        name
    );
  }
}
