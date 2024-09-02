package com.example.demo.totp;


import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;
import com.warrenstrange.googleauth.GoogleAuthenticatorQRGenerator;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class GenerateTotp {

    private final GoogleAuthenticator authenticator = new GoogleAuthenticator();




    public static void main(String[] args) throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        final GoogleAuthenticator authenticator = new GoogleAuthenticator();
        final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        GoogleAuthenticatorKey credentials = authenticator.createCredentials();
        String key = credentials.getKey();


        String otpAuthURL = GoogleAuthenticatorQRGenerator.getOtpAuthTotpURL("Karrabo", "OLamilekaN", credentials);
        System.out.println(otpAuthURL);
        System.out.println(key);

        String kley = encryptUserSecret(key);

        System.out.println("kuy=============++>>>>>>>>"+kley);

        System.out.println("decoded secretKey ==================++++++>>>>>>" + decryptUserSecret(kley));




        System.out.println(validateCode(152368, "PL6DPTTH62ZPWKOBH6P7AXH3ZLMTFSOY"));

    }

    public static boolean validateCode(int code, String key){
        GoogleAuthenticator googleAuthenticator = new GoogleAuthenticator();
        return googleAuthenticator.authorize(key, code);
    }

    public static String encryptUserSecret(String userSecret) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        byte[] secretKey = "1234567812345678".getBytes();
        byte[] iv ="1234567812345678".getBytes();
        byte[] data = userSecret.getBytes();

        Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec key = new SecretKeySpec(secretKey, "AES");
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
        instance.init(Cipher.ENCRYPT_MODE, key, ivParameterSpec);

        byte[] encryptedData = instance.doFinal(data);

        return Base64.getEncoder().encodeToString(encryptedData);
    }

    public static String decryptUserSecret(String encryptedUserSecret) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        byte[] secretKey = "1234567812345678".getBytes();
        byte[] iv ="1234567812345678".getBytes();

        Cipher decryptor = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec key = new SecretKeySpec(secretKey, "AES");
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

        decryptor.init(Cipher.DECRYPT_MODE, key, ivParameterSpec);
        byte[] bytes = decryptor.doFinal(Base64.getDecoder().decode(encryptedUserSecret));

        return new String(bytes);

    }


    public void generateTotp() {
        authenticator.createCredentials();
    }
}
