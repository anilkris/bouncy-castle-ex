package com.myc.aztik;

import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import javax.crypto.SecretKey;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EncryptDecryptTest {

	@Test
	public void ecbEncryptDecryptTest() throws GeneralSecurityException {
		SymmetricKeyGen genKey = new SymmetricKeyGen();
		SecretKey key = genKey.ex4GenerateKey();

		ECBEncryptDecrypt encryptDecrypt = new ECBEncryptDecrypt();

		String secretData = "This is a secret to be Encrypted.";
		byte[] data = secretData.getBytes();
		byte[] encryptedData = encryptDecrypt.ecbEncrypt(key, data);

		byte[] decryptedData = encryptDecrypt.ecbDecrypt(key, encryptedData);

		String decryptString = new String(decryptedData);

		Assertions.assertArrayEquals(data, decryptedData);

		Assertions.assertEquals(secretData, decryptString);

	}

	@Test
	public void cbcEncryptDecryptTest() throws GeneralSecurityException {
		
		SymmetricKeyGen genKey = new SymmetricKeyGen();

		SecretKey key = genKey.ex4GenerateKey();

		String secretData = "This is a secret to be Encrypted.";
		byte[] plaintext = secretData.getBytes();
		
		CBCEncryptDecrypt cBCEncryptDecrypt  = new CBCEncryptDecrypt();  

		byte[][] ivCiphertext = cBCEncryptDecrypt.cbcEncrypt(key, plaintext);

		byte[] iv = ivCiphertext[0];
		Assertions.assertNotNull(iv);
		byte[] ciphertext = ivCiphertext[1];
		Assertions.assertNotNull(ciphertext);

		byte[] derivedPlainText = cBCEncryptDecrypt.cbcDecrypt(key, iv, ciphertext);
		Assertions.assertNotNull(derivedPlainText);
		Assertions.assertArrayEquals(plaintext, derivedPlainText);
	}

}
