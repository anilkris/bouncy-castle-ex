package com.myc.aztik;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.TrustManagerFactory;

public class CertificatePinning {

    public void certificatePnningTrust() throws MalformedURLException, IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException, CertificateException {
        final String hostname = "https://www.packtpub.com";
        SSLContext sslContext = SSLContext.getInstance("TLSv1.2");
        KeyManager[] keyManagers = {};
        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        KeyStore truststore = KeyStore.getInstance(KeyStore.getDefaultType());
        truststore.load(CertificatePinning.class.getResourceAsStream("keystore"), "changeit".toCharArray());
        trustManagerFactory.init(truststore);
        sslContext.init(keyManagers, trustManagerFactory.getTrustManagers(), new SecureRandom());

        final URL url = new URL(hostname);
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
        conn.setSSLSocketFactory(sslContext.getSocketFactory());
        System.out.println("Testing connection with one certificate...");
        System.out.println("  Commands to test are in createkeystore.txt");
        try {
            conn.connect();
            System.out.println("Connected");
            try (InputStream in = conn.getInputStream()) {
                final ByteArrayOutputStream bout = new ByteArrayOutputStream();
                final byte[] bytes = new byte[1024];
                
                //I don't want to read the full html but this will do it.
                //for (int length = in.read(bytes); length != -1; length = in.read(bytes)) {
                //    bout.write(bytes, 0, length);
                //}
                final int length = in.read(bytes);
                bout.write(bytes, 0, length);
                System.out.println(new String(bout.toByteArray()));
            }
        } catch (SSLHandshakeException e) {
            Logger.getLogger(CertificatePinning.class.getSimpleName()).log(Level.SEVERE, "Certificate pin missing", e);
        } catch (SSLException e) {
            Logger.getLogger(CertificatePinning.class.getSimpleName()).log(Level.SEVERE, "Unable to access valid keystore", e);
        }
    }
    
   public void certificatePnningTrust2() throws URISyntaxException {
	   
	  URL resource = CertificatePinning.class.getResource("keystore.jks");
	   System.setProperty( "javax.net.ssl.trustStore", resource.toURI().getPath() );
	   
	  File file = new File(resource.toURI());

	     try (FileInputStream truststoreFile = new FileInputStream(file)) {
	            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
	            KeyStore truststore = KeyStore.getInstance("JKS");
	            char[] trustorePassword = "changeit".toCharArray();
	            truststore.load(truststoreFile, trustorePassword);
	            trustManagerFactory.init(truststore);
	            SSLContext sslContext = SSLContext.getInstance("TLSv1.2");
	            KeyManager[] keyManagers = {};//if you have key managers;

	            sslContext.init(keyManagers, trustManagerFactory.getTrustManagers(), new SecureRandom());

	            URL httpsUrl = new URL("https://www.packtpub.com");
	            HttpsURLConnection urlConnection = (HttpsURLConnection) httpsUrl.openConnection();
	            urlConnection.setSSLSocketFactory(sslContext.getSocketFactory());
	         
	            try {
	            	urlConnection.connect();
	                System.out.println("Connected");
	                try (InputStream in = urlConnection.getInputStream()) {
	                    final ByteArrayOutputStream bout = new ByteArrayOutputStream();
	                    final byte[] bytes = new byte[1024];
	                    
	                    //I don't want to read the full html but this will do it.
	                    //for (int length = in.read(bytes); length != -1; length = in.read(bytes)) {
	                    //    bout.write(bytes, 0, length);
	                    //}
	                    final int length = in.read(bytes);
	                    bout.write(bytes, 0, length);
	                    System.out.println(new String(bout.toByteArray()));
	                }
	            } catch (SSLHandshakeException e) {
	                Logger.getLogger(CertificatePinning.class.getSimpleName()).log(Level.SEVERE, "Certificate pin missing", e);
	            } catch (SSLException e) {
	                Logger.getLogger(CertificatePinning.class.getSimpleName()).log(Level.SEVERE, "Unable to access valid keystore", e);
	            }


	        } catch (NoSuchAlgorithmException | KeyStoreException | CertificateException | IOException e) {
	            //handle exception
	        } catch (KeyManagementException e) {
	           //handle exception
	        }
   }
   
   
   
}
