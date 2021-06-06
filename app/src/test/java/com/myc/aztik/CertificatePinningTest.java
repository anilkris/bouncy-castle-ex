package com.myc.aztik;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

import org.junit.jupiter.api.Test;

public class CertificatePinningTest {
	
	@Test
	public void certficateTest() throws KeyManagementException, MalformedURLException, NoSuchAlgorithmException, KeyStoreException, CertificateException, IOException, URISyntaxException {
		CertificatePinning certificatePinning = new CertificatePinning();
		certificatePinning.certificatePnningTrust();
		
	}

}
