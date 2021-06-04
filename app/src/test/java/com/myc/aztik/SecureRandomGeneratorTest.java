package com.myc.aztik;

import java.security.SecureRandom;

import org.junit.jupiter.api.Test;
import org.bouncycastle.crypto.fips.FipsDRBG;
import com.myc.aztik.common.ExValues;

public class SecureRandomGeneratorTest {


	@Test
	public void randomNumberTest() {
		SecureRandomGenerator random = new SecureRandomGenerator();
		
		SecureRandom sec = random.buildDrbg();
		
		System.out.println(sec.nextLong());
		
	}
}
