package com.woonjin.blog.config.utils;

public interface SignatureEncoder {

  String encode(
      String r,
      String s,
      Long chainId
  );
}
