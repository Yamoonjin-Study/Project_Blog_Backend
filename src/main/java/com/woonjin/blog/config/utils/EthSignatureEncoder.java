package com.woonjin.blog.config.utils;

import io.haechi.henesis.util.Converter;
import org.web3j.rlp.RlpEncoder;
import org.web3j.rlp.RlpList;
import org.web3j.rlp.RlpString;
import org.web3j.rlp.RlpType;
import org.web3j.utils.Bytes;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EthSignatureEncoder implements SignatureEncoder {

  private static final int CHAIN_ID_INC = 35;
  private static final int LOWER_REAL_V = 27;

  @Override
  public String encode(String r, String s, Long recoveryId) {
    BigInteger v = BigInteger.valueOf(recoveryId + 26);
    v = v.subtract(BigInteger.valueOf(LOWER_REAL_V));
    v = v.add(BigInteger.valueOf(chainId * 2));
    v = v.add(BigInteger.valueOf(CHAIN_ID_INC));

    List<RlpType> values = new ArrayList<>();
    values.add(RlpString.create(Bytes.trimLeadingZeroes(v.toByteArray())));
    values.add(
        RlpString
            .create(Bytes.trimLeadingZeroes(
                Objects.requireNonNull(Converter.hexStringToByteArray(r))
            ))
    );
    values.add(
        RlpString
            .create(Bytes.trimLeadingZeroes(
                Objects.requireNonNull(Converter.hexStringToByteArray(s))
            ))
    );
    RlpList rlpList = new RlpList(values);
    return Converter.byteArrayToHexString(RlpEncoder.encode(rlpList));
  }
}
