package com.ewypass.ezypass;

import android.util.Base64;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

class Generator {

    /**
     *
     * @return
     */
    static SecretKey generateUserKey(){
        KeyGenerator keyGen = null;
        try {
            keyGen = KeyGenerator.getInstance("AES");
            keyGen.init(256); // for example
            return keyGen.generateKey();
            //Base64.encodeToString(secretKey.getEncoded(), Base64.DEFAULT);
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
    static SecretKey importSecretKey(String imported){
        // decode the base64 encoded string
        byte[] decodedKey = Base64.decode(imported, Base64.DEFAULT);

        // rebuild key using SecretKeySpec
        return new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
    }

    /**
     * TODO : update encryption algorithm
     * @param appName
     * @param userKey
     * @param size
     * @return
     */
    static String generateUserPass(String appName, SecretKey userKey, int size){
        // Create PBE Cipher
        Cipher cipher;
        try {
            cipher = Cipher.getInstance("PBEWithMD5AndDES");

            // Initialize PBE Cipher with key and parameters
            cipher.init(Cipher.ENCRYPT_MODE, userKey);

            // Encrypt
            String encrypted = Arrays.toString(cipher.doFinal(appName.getBytes()));

            return encrypted.substring(0, size);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | BadPaddingException | IllegalBlockSizeException | InvalidKeyException e) {
            e.printStackTrace();
        }

        return null;
    }
}
