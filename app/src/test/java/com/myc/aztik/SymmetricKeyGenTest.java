package com.myc.aztik;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;

import javax.crypto.SecretKey;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SymmetricKeyGenTest {
	
	@Test
	public void ex4SymmetricKeyGenTest() throws NoSuchAlgorithmException, NoSuchProviderException {
		SymmetricKeyGen genKey = new SymmetricKeyGen();
		SecretKey key = genKey.ex4GenerateKey();
		
		
		String encodedString = genKey.encodeKey(key);
		System.out.println(encodedString);
		
		
		Assertions.assertEquals(key, genKey.decodeKey(encodedString));
	}

	@Test
	public void ex5SecretWithPreDefinedKeyTest() throws NoSuchAlgorithmException, NoSuchProviderException {
		SymmetricKeyGen genKey = new SymmetricKeyGen();
        SecureRandom sr = new SecureRandom();

        byte[] initVector = new byte[16];
        sr.nextBytes(initVector);
		SecretKey key = genKey.ex5SecretWithPreDefinedKey(initVector);
		
		
		String encodedString = genKey.encodeKey(key);
		System.out.println(encodedString);
		
		
		Assertions.assertEquals(key, genKey.decodeKey(encodedString));
	}

	

}
