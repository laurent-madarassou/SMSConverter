package tools;

/**
 * Created by user on 24/01/18.
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


public class CryptTools {



       private  static  byte[]   keyValue = new byte[]{'T', 'h', 'e', 'A', 'i', 's', 't', 'Z', 'e', 'c', 'a', 'e', 'i', 'J', 'e', 'y'};
       private  static String CryptMethode = "AES/CBC/PKCS5Padding";
       private static LogError logError;

    private static String  errorMessage;


    public  static LogError EncryptDecryptFile(int cipherMode ,File inputFile,File outputFile){

        LogError logerror = new LogError();
        logerror.setError_status(true);
        logerror.setError_message("No Error");

        try {


            Key secretKey = new SecretKeySpec(keyValue, CryptMethode);
            Cipher cipher = Cipher.getInstance(CryptMethode);

            byte[] iv = new byte[cipher.getBlockSize()];

            IvParameterSpec ivParams = new IvParameterSpec(iv);
            cipher.init(cipherMode, secretKey ,ivParams);


            FileInputStream inputStream = new FileInputStream(inputFile);
            byte[] inputBytes = new byte[(int) inputFile.length()];
            inputStream.read(inputBytes);

            byte[] outputBytes = cipher.doFinal(inputBytes);

            FileOutputStream outputStream = new FileOutputStream(outputFile);
            outputStream.write(outputBytes);

            inputStream.close();
            outputStream.close();

        } catch (NoSuchPaddingException | NoSuchAlgorithmException
                | InvalidKeyException | InvalidAlgorithmParameterException | BadPaddingException
                | IllegalBlockSizeException | IOException e) {
            e.printStackTrace();
            errorMessage =  e.getLocalizedMessage();
            logError = new LogError();
            logError.setError_message("EncryptDecryptFile   : " + e.getMessage());
            logError.setError_status(false);
            return logError;
            //TODO logError.send("EncryptDecryptFile   : " + e.getMessage() )
            //ErrorLog.ecrireErreur(errorMessage + " -- CryptTools::EncryptDecryptFile");

        }
        return logError;
    }


    public static byte[] encryptX(String value) {
        byte[] encrypted = null;
        try {

            Key skeySpec = new SecretKeySpec(keyValue, "AES");
            Cipher cipher = Cipher.getInstance(CryptMethode);
            byte[] iv = new byte[cipher.getBlockSize()];

            IvParameterSpec ivParams = new IvParameterSpec(iv);
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec,ivParams);
            encrypted  = cipher.doFinal(value.getBytes());
            System.out.println("encrypted string:" + encrypted.length);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return encrypted;

    }
    public static  byte[]  decryptX(byte[] encrypted) {
        byte[] original = null;
        Cipher cipher = null;
        try {

            Key key = new SecretKeySpec(keyValue, "AES");
            cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

            byte[] ivByte = new byte[cipher.getBlockSize()];
            IvParameterSpec ivParamsSpec = new IvParameterSpec(ivByte);
            cipher.init(Cipher.DECRYPT_MODE, key, ivParamsSpec);
            original= cipher.doFinal(encrypted);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return original;
    }

}

