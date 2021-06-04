package com.myc.aztik;

import java.security.SecureRandom;

import org.bouncycastle.crypto.EntropySourceProvider;
import org.bouncycastle.crypto.fips.FipsDRBG;
import org.bouncycastle.crypto.util.BasicEntropySourceProvider;

import com.myc.aztik.common.ExValues;

public class SecureRandomGenerator {

	public SecureRandom ex1BuildDrbg() {
		// The generateSeed() method of a regular SecureRandom is used as the
		// entropy source. This makes use of any entropy source the running
		// JVM is configured for.
		EntropySourceProvider entSource = new BasicEntropySourceProvider(new SecureRandom(), true);

		FipsDRBG.Builder drgbBldr = FipsDRBG.SHA512_HMAC.fromEntropySource(entSource).setSecurityStrength(256)
				.setEntropyBitsRequired(256);

		// 1. The returned SecureRandom is not created via the Java provider.
		// It returns FipsSecureRandom which extends SecureRandom.
		// The extension class is necessary as a NIST DRBG requires methods
		// such as FipsSecureRandom.reseed()

		// 2. The “prediction resistance” parameter on the FipsDRBG.Builder.build()
		// method is set to false. It's set to false, as it's assumed that
		// the DRBG function will do a good job in producing a random stream.
		// In the case of keys or components of keys, a higher standard
		// is needed. It is achieved by setting to be “prediction resistance”
		// to true.
		return drgbBldr.build(ExValues.Nonce, false);
	}

	public SecureRandom ex2BuildDrbgForKeys() {
		// The generateSeed() method of a regular SecureRandom is used as the
		// entropy source. This makes use of any entropy source the running
		// JVM is configured for.
		EntropySourceProvider entSource = new BasicEntropySourceProvider(new SecureRandom(), true);

		FipsDRBG.Builder drgbBldr = FipsDRBG.SHA512_HMAC.fromEntropySource(entSource).setSecurityStrength(256)
				.setEntropyBitsRequired(256).setPersonalizationString(ExValues.PersonalizationString);

		// 1. The returned SecureRandom is not created via the Java provider.
		// It returns FipsSecureRandom which extends SecureRandom.
		// The extension class is necessary as a NIST DRBG requires methods
		// such as FipsSecureRandom.reseed()

		// 2. The “prediction resistance” parameter on the FipsDRBG.Builder.build()
		// method is set to false. It's set to false, as it's assumed that
		// the DRBG function will do a good job in producing a random stream.
		// In the case of keys or components of keys, a higher standard
		// is needed. It is achieved by setting to be “prediction resistance”
		// to true.
		return drgbBldr.build(ExValues.Nonce, true);
	}

	public SecureRandom ex3DefaultSecureRandom() {
		// The generateSeed() method of a regular SecureRandom is used as the
		// entropy source. This makes use of any entropy source the running
		// JVM is configured for.
		EntropySourceProvider entSource = new BasicEntropySourceProvider(new SecureRandom(), true);

		FipsDRBG.Builder drgbBldr = FipsDRBG.SHA512.fromEntropySource(entSource)
							.setSecurityStrength(256)
							.setEntropyBitsRequired(256);

		// 1. The returned SecureRandom is not created via the Java provider.
		// It returns FipsSecureRandom which extends SecureRandom.
		// The extension class is necessary as a NIST DRBG requires methods
		// such as FipsSecureRandom.reseed()

		// 2. The “prediction resistance” parameter on the FipsDRBG.Builder.build()
		// method is set to false. It's set to false, as it's assumed that
		// the DRBG function will do a good job in producing a random stream.
		// In the case of keys or components of keys, a higher standard
		// is needed. It is achieved by setting to be “prediction resistance”
		// to true.
		return drgbBldr.build(ExValues.Nonce, true);
	}

}
