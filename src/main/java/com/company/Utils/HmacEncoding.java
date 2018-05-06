package com.company.Utils;

import org.spongycastle.util.encoders.Hex;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class HmacEncoding {

    public static String encode(String key, String data, String encoding) {
        Mac Hmac = null;
        try {
            Hmac = Mac.getInstance(encoding);

            SecretKeySpec secret_key = new SecretKeySpec(key.getBytes("UTF-8"), encoding);
            Hmac.init(secret_key);

            return Hex.toHexString(Hmac.doFinal(data.getBytes("UTF-8")));
        } catch (NoSuchAlgorithmException | InvalidKeyException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
