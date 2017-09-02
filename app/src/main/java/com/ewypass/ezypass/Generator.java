package com.ewypass.ezypass;

import android.util.Base64;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

class Generator {

    private static final int USER_KEY_SIZE = 192;

    /**
     *
     * @return
     */
    public static SecretKey generateUserKey(){
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance("DESede");
            keyGen.init(USER_KEY_SIZE); // for example
            return keyGen.generateKey();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @param imported
     * @return
     */
    public static SecretKey importSecretKey(String imported){
        // decode the base64 encoded string
        byte[] decodedKey = Base64.decode(imported, Base64.DEFAULT);

        // rebuild key using SecretKeySpec
        return new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
    }

    /**
     *
     * @param key
     * @return
     */
    public static String keyToString(SecretKey key){
        return Base64.encodeToString(key.getEncoded(), Base64.DEFAULT);
    }

    /**
     * TODO : update encryption algorithm
     * @param appName
     * @param userKey
     * @param size
     * @return
     */
    public static String generateUserPass(String appName, SecretKey userKey, int size){
        try {
            byte[] fullByteKey = (appName + userKey).getBytes("UTF-8");
            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            byte[] key = sha.digest(fullByteKey);
            key = Arrays.copyOf(key, 16); // use only first 128 bit

            SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
            String resultingKey = keyToString(secretKeySpec);
            return resultingKey.substring(0, size);
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
