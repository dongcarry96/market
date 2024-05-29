package com.example.userservice.global.config;


import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;

public class AES256Util {

    private static final String ALGORITHM = "AES";
    private static final String SECRET_KEY = "7o22Saz4et7eo12e4567a90f23n9d7c6"; // 비밀키 (16자 이상)

    // AES 암호화
    public static String encrypt(String value) {
        try {
            Key key = generateKey();
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encryptedByteValue = cipher.doFinal(value.getBytes("utf-8"));
            return Base64.getEncoder().encodeToString(encryptedByteValue);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // AES 복호화
    public static String decrypt(String value) {
        try {
            Key key = generateKey();
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] decryptedValue64 = Base64.getDecoder().decode(value);
            byte[] decryptedByteValue = cipher.doFinal(decryptedValue64);
            return new String(decryptedByteValue);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // 비밀키 생성
    private static Key generateKey() throws Exception {
        return new SecretKeySpec(SECRET_KEY.getBytes(), ALGORITHM);
    }
}
