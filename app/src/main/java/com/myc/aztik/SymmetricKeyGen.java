package com.myc.aztik;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;
import java.util.Base64;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.jcajce.provider.BouncyCastleFipsProvider;

public class SymmetricKeyGen {
	
	public SymmetricKeyGen() {
	       Security.addProvider(new BouncyCastleFipsProvider());

	}
	
	public SecretKey ex4GenerateKey() throws NoSuchAlgorithmException, NoSuchProviderException {
		

		KeyGenerator keyGenerator = KeyGenerator.getInstance("AES", "BCFIPS");
		keyGenerator.init(256);
		return keyGenerator.generateKey();
	}
	
	public SecretKey ex5SecretWithPreDefinedKey(byte[] keyBytes) {
	
		if (keyBytes.length != 16 && keyBytes.length != 24 && keyBytes.length != 32) {
            throw new IllegalArgumentException(
                    "keyBytes wrong length for AES key");
        }
        return new SecretKeySpec(keyBytes, "AES");
	}
	
	public String encodeKey(SecretKey key) {
		String encoded = Base64.getEncoder().encodeToString(key.getEncoded());
		return encoded;
		
	}
	
	public SecretKey decodeKey(String encodedKey) {
		byte[] decodedKey = Base64.getDecoder().decode(encodedKey);
		// rebuild key using SecretKeySpec
		SecretKey originalKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES"); 
		return originalKey;
	}
	
	

}
