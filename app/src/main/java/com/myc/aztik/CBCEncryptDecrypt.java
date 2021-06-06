package com.myc.aztik;

import java.security.GeneralSecurityException;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

public class CBCEncryptDecrypt {

	  public byte[][] cbcEncrypt(SecretKey key, byte[] data)
	            throws GeneralSecurityException {
	        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding", "BCFIPS");
	        cipher.init(Cipher.ENCRYPT_MODE, key);

	        // CBC mode also has an extra parameter, the initialization
	        // vector (IV), which is used with the mode to prevent any
	        // obvious similarities that might have existed in two plain texts
	        // from showing up in the encrypted results
	        return new byte[][]{cipher.getIV(), cipher.doFinal(data)};
	    }

	    /**
	     * Decrypts the data in CBC (Cipher Block Chaining) mode.
	     * <p>
	     * Example 7b
	     *
	     * @param key        the secret key used for decryption
	     * @param iv         initialization vector
	     * @param cipherText an encrypted ciphertext
	     * @return
	     * @throws GeneralSecurityException
	     */
	    public byte[] cbcDecrypt(SecretKey key, byte[] iv, byte[] cipherText)
	            throws GeneralSecurityException {
	        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding", "BCFIPS");
	        cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(iv));
	        return cipher.doFinal(cipherText);
	    }
}
