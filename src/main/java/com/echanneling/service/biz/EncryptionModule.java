package com.echanneling.service.biz;

import com.echanneling.delegate.AppDelegate;
import com.echanneling.model.Constants;
import com.echanneling.service.support.CatchException;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

/**
 * @author shalithasenanayaka on 2019-08-13 using IntelliJ IDEA
 */
public class EncryptionModule {
    /**
     * Encryption logic
     */

    private static String secretKey;
    private static String salt;

    static {
        secretKey = AppDelegate.properties.getProperty(Constants.SECRET_KEY);
        salt = AppDelegate.properties.getProperty(Constants.SALT);
    }


    public static String Encrypt(String value) {

        try
        {
            byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
            IvParameterSpec ivspec = new IvParameterSpec(iv);

            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            KeySpec spec = new PBEKeySpec(secretKey.toCharArray(), salt.getBytes(), 65536, 256);
            SecretKey tmp = factory.generateSecret(spec);
            SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec);
            return Base64.getEncoder().encodeToString(cipher.doFinal(value.getBytes("UTF-8")));
        }
        catch (InvalidKeySpecException e) {
            CatchException.Handle(e);
        } catch (NoSuchAlgorithmException e) {
            CatchException.Handle(e);
        } catch (BadPaddingException e) {
            CatchException.Handle(e);
        } catch (InvalidKeyException e) {
            CatchException.Handle(e);
        } catch (InvalidAlgorithmParameterException e) {
            CatchException.Handle(e);
        } catch (NoSuchPaddingException e) {
            CatchException.Handle(e);
        } catch (IllegalBlockSizeException e) {
            CatchException.Handle(e);
        } catch (UnsupportedEncodingException e) {
            CatchException.Handle(e);
        }
        return null;

    }

    public static String Descrypt(String value) {
        try
        {
            byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
            IvParameterSpec ivspec = new IvParameterSpec(iv);

            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            KeySpec spec = new PBEKeySpec(secretKey.toCharArray(), salt.getBytes(), 65536, 256);
            SecretKey tmp = factory.generateSecret(spec);
            SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec);
            return new String(cipher.doFinal(Base64.getDecoder().decode(value)));
        } catch (InvalidKeySpecException e) {
            CatchException.Handle(e);
        } catch (NoSuchAlgorithmException e) {
            CatchException.Handle(e);
        } catch (BadPaddingException e) {
            CatchException.Handle(e);
        } catch (InvalidKeyException e) {
            CatchException.Handle(e);
        } catch (InvalidAlgorithmParameterException e) {
            CatchException.Handle(e);
        } catch (NoSuchPaddingException e) {
            CatchException.Handle(e);
        } catch (IllegalBlockSizeException e) {
            CatchException.Handle(e);
        }

        return null;
    }
}
