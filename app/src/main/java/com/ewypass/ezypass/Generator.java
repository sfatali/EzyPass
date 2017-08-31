package com.ewypass.ezypass;

import android.util.Base64;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

class Generator {

    static final int USER_KEY_SIZE = 192;

    /**
     *
     * @return
     */
    static SecretKey generateUserKey(){
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
        Base64.encodeToString(userKey.getEncoded(), Base64.DEFAULT);
        /*byte[] key = (SALT2 + username + password).getBytes("UTF-8");
        MessageDigest sha = MessageDigest.getInstance("SHA-1");
        key = sha.digest(key);
        key = Arrays.copyOf(key, 16); // use only first 128 bit

        SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");*/

        try {
            Cipher cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, userKey);

            // Encrypt
            String encrypted = Base64.encodeToString(cipher.doFinal(appName.getBytes("UTF-8")), Base64.DEFAULT);

            return encrypted.substring(0, size);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | BadPaddingException | IllegalBlockSizeException | InvalidKeyException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return null;
    }
}
