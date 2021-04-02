package com.woonjin.blog.config.utils;

import io.haechi.henesis.util.Converter;
import org.bitcoinj.core.ECKey.ECDSASignature;
import org.bitcoinj.core.Transaction.SigHash;
import org.bitcoinj.crypto.TransactionSignature;

import javax.annotation.Nullable;

public class BtcSignatureEncoder implements SignatureEncoder {

  @Override
  public String encode(String r, String s, @Nullable Long chainId) {
    return Converter.byteArrayToHexString(
        new TransactionSignature(
            new ECDSASignature(
                Converter.hexStringToBigInteger(r),
                Converter.hexStringToBigInteger(s)
            ),
            SigHash.ALL,
            false
        ).encodeToBitcoin()
    );
  }
}
