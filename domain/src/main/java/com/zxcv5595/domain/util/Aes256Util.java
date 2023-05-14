package com.zxcv5595.domain.util;

import java.nio.charset.StandardCharsets;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.apache.tomcat.util.codec.binary.Base64;

public class Aes256Util {

    private static final String KEY = "SECRETKEYISSECRETKEYKEYSECRETKEY";
    private static final String IV = KEY.substring(0, 16);
    public static String alg = "AES/CBC/PKCS5Padding";

    public static String encrypt(String text) {
        try {
            Cipher cipher = Cipher.getInstance(alg);
            SecretKeySpec keySpec = new SecretKeySpec(KEY.getBytes(StandardCharsets.UTF_8), "AES");
            IvParameterSpec ivParameterSpec = new IvParameterSpec(IV.getBytes(StandardCharsets.UTF_8));
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivParameterSpec);

            byte[] encrypted = cipher.doFinal(text.getBytes(StandardCharsets.UTF_8));
            return Base64.encodeBase64String(encrypted);
        }catch (Exception e){
            return null;
        }
    }

    public static String decrypt(String cipherText){
        try{
            Cipher cipher = Cipher.getInstance(alg);
            SecretKeySpec keySpec = new SecretKeySpec(KEY.getBytes(StandardCharsets.UTF_8),"AES");
            IvParameterSpec ivParameterSpec = new IvParameterSpec(IV.getBytes(StandardCharsets.UTF_8));
            cipher.init(Cipher.DECRYPT_MODE,keySpec,ivParameterSpec);
            byte[] decodeBytes = Base64.decodeBase64(cipherText);
            byte[] decrypted = cipher.doFinal(decodeBytes);
            return new String(decrypted,StandardCharsets.UTF_8);
        }catch (Exception e){
            return null;
        }
    }

}
