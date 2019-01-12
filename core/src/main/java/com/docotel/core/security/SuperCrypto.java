package com.docotel.core.security;

import android.util.Base64;
import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import androidx.annotation.NonNull;

/**
 * Created by Lafran on 12/18/17.
 */

public final class SuperCrypto {
    public static SuperCrypto instance;

    static {
        System.loadLibrary("hello-jni");

        if (instance == null) {
            instance = new SuperCrypto();
        }
    }

    public static String hiddenKey = "75XqcIlk7jYdD4qJ";

    public static String ivKey = "VH93F1SUXP5I4SJU";

    public static String mode = "AES/CBC/PKCS7Padding";

    public static String algho = "SHA-256";

    private final String expiredMessage = "expired";
    private final String failed = "* intruder!! *";

    private String AES_MODE = "";
    private String HASH_ALGORITHM = "";

    private final String TAG = "SuperCrypto";
    private final String CHARSET = "UTF-8";
    private final Long TIME_DIFF_LIMIT = 3600L * 1;
    private static final String CHAR_REG = "¡";

    private static boolean DEBUG_LOG_ENABLED = true;


    public static String obfuscate(String message) {
        try {
            return instance.encrypt(message);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
        return "failed to encrypt";
    }

    public static String deobfuscate(String message) {
        try {
            return instance.decrypt(message);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
        return instance.failed;
    }

    private String encrypt(String readable)
            throws GeneralSecurityException {

        try {
            String toHide = compose(readable);
            String password = hiddenKey;
            final SecretKeySpec key = generateKey(password);

            byte[] ivBytes = generateIv();
            byte[] cipherText = encryptInBytes(key, ivBytes, toHide.getBytes(CHARSET));

            return Base64.encodeToString(cipherText, Base64.NO_WRAP);
        } catch (UnsupportedEncodingException e) {
            if (DEBUG_LOG_ENABLED)
                Log.e(TAG, "UnsupportedEncodingException ", e);
            throw new GeneralSecurityException(e);
        }
    }

    @NonNull
    private String compose(String message) {
        return hiddenTime() + CHAR_REG + message;
    }

    private String decompose(String message) {
        String[] strArray = message.split(CHAR_REG);
        if (strArray.length >= 2) {
            String strRevTime = reversed(strArray[0]);
            Long time = Long.parseLong(strRevTime);
            if (isHasChance(time)) {
                return strArray[strArray.length - 1];
            } else {
                return expiredMessage;
            }
        }
        return "* this is secret message *";
    }

    private boolean isHasChance(Long ts) {
        Long current = Long.parseLong(currentTimeInterval());
        Long gap = Math.abs(ts - current);
        return gap <= TIME_DIFF_LIMIT;
    }

    private byte[] encryptInBytes(final SecretKeySpec key, final byte[] iv, final byte[] message)
            throws GeneralSecurityException {
        final Cipher cipher = Cipher.getInstance(AES_MODE);
        IvParameterSpec ivSpec = new IvParameterSpec(iv);
        cipher.init(Cipher.ENCRYPT_MODE, key, ivSpec);
        return cipher.doFinal(message);
    }

    private String decrypt(String unreadable)
            throws GeneralSecurityException {

        try {
            String password = hiddenKey;
            final SecretKeySpec key = generateKey(password);

            byte[] decodedCipherText = Base64.decode(unreadable, Base64.NO_WRAP);
            byte[] ivBytes = generateIv();
            byte[] decryptedBytes = decryptFromBytes(key, ivBytes, decodedCipherText);

            String message = new String(decryptedBytes, CHARSET);
            String result = decompose(message);

            return result;
        } catch (UnsupportedEncodingException e) {
            if (DEBUG_LOG_ENABLED)
                Log.e(TAG, "UnsupportedEncodingException ", e);

            throw new GeneralSecurityException(e);
        }
    }

    private byte[] decryptFromBytes(final SecretKeySpec key, final byte[] iv, final byte[] decodedCipherText)
            throws GeneralSecurityException {
        final Cipher cipher = Cipher.getInstance(AES_MODE);
        IvParameterSpec ivSpec = new IvParameterSpec(iv);
        cipher.init(Cipher.DECRYPT_MODE, key, ivSpec);
        return cipher.doFinal(decodedCipherText);
    }

    @NonNull
    private String currentTimeInterval() {
        String timeSince1970Str = String.valueOf((System.currentTimeMillis() / 1000));
        return timeSince1970Str.substring(0, 10);
    }

    private byte[] generateIv() {
        final byte[] bytes = ivKey.getBytes(StandardCharsets.UTF_8);
        byte[] finalBytes = new byte[bytes.length];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            finalBytes[j] = (byte) v;
        }
        return finalBytes;
    }

    private final SecretKeySpec generateKey(final String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        final MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
        byte[] bytes = password.getBytes(CHARSET);
        digest.update(bytes, 0, bytes.length);
        byte[] key = digest.digest();
        return new SecretKeySpec(key, "AES");
    }

    private String reversed(String target) {
        return new StringBuilder(target).reverse().toString();
    }

    private String hiddenTime() {
        String time = currentTimeInterval();
        return reversed(time);
    }

    private SuperCrypto() {
        AES_MODE = mode;
        HASH_ALGORITHM = algho;
    }
}
