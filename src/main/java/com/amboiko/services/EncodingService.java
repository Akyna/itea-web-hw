package com.amboiko.services;

import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class EncodingService {
    private static final String SALT = "com.amboiko";

    public static String md5Encryption(String inputData) {
        String result = null;

        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(StandardCharsets.UTF_8.encode(inputData));
            result = String.format("%032x", new BigInteger(messageDigest.digest()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static String md5EncryptionWithSalt(String inputData) {
        return md5Encryption(inputData + SALT);
    }
}
