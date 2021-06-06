package com.myc.aztik;

import java.security.GeneralSecurityException;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;

public class ECBEncryptDecrypt {

	public byte[] ecbEncrypt(SecretKey key, byte[] data) throws GeneralSecurityException {
		// since many block cipher modes is unpadded so the input has to be
		// aligned on the block boundaries of the cipher - in this case 128 bits.
		// To get around this restriction, “PKCS7Padding” is specified instead
		// of "NoPadding" to allow for non-block aligned data
		// PKCS7Padding is often also referred to as PKCS5Padding and the
		// BC APIs provide a number of other padding mechanisms such as
		// ISO10126-2, X9.23, ISO7816-4, and TBC (trailing bit compliment)
		// padding.
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS7Padding", "BCFIPS");
		cipher.init(Cipher.ENCRYPT_MODE, key);
		return cipher.doFinal(data);
	}

	public byte[] ecbDecrypt(SecretKey key, byte[] cipherText) throws GeneralSecurityException {
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS7Padding", "BCFIPS");
		cipher.init(Cipher.DECRYPT_MODE, key);
		return cipher.doFinal(cipherText);
	}

}
