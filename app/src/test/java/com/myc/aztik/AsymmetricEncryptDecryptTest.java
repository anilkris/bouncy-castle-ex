package com.myc.aztik;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.junit.jupiter.api.Test;

public class AsymmetricEncryptDecryptTest {
	
	@Test
	public void testSelf() throws InvalidKeyException, SignatureException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		AsymmetricEncryptDecryptSelf asymmetricEncryptDecrypt = new AsymmetricEncryptDecryptSelf();
		asymmetricEncryptDecrypt.encryptDecrypt();
	}
	
	
	@Test
	public void test() throws InvalidKeyException, SignatureException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		AsymmetricEncryptDecrypt asymmetricEncryptDecrypt = new AsymmetricEncryptDecrypt();
		asymmetricEncryptDecrypt.encryptDecrypt();
	}


	
	

}
