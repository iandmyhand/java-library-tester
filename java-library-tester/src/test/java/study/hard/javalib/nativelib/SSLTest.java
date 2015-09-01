package study.hard.javalib.nativelib;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

import org.junit.Before;
import org.junit.Test;

import study.hard.javalib.service.CustomizedSSLSocketFactory;

public class SSLTest {

	@Before
	public void checkSystemProperty() {
		System.out.println("java.version: " + System.getProperty("java.version"));

		// If you're going to use one of JDK 6 Updates 19-21, JDK 5 Updates 24-25 or JDK 4 Updates 26-27 as Java compiler for this project,
		// you have to uncomment below code. Refer to http://www.oracle.com/technetwork/java/javase/documentation/tlsreadme2-176330.html
		//		System.setProperty("sun.security.ssl.allowUnsafeRenegotiation", "true");

		// setProxy();
	}

	/**
	 * To use local proxy.
	 */
	private void setProxy() {
		System.setProperty("http.proxyHost", "127.0.0.1");
		System.setProperty("https.proxyHost", "127.0.0.1");
		System.setProperty("http.proxyPort", "8888");
		System.setProperty("https.proxyPort", "8888");
	}

	@Test
	public void testUsingCustomizedSSLSocketFactory() {
		URL url = null;
		CustomizedSSLSocketFactory factory = null;
		HttpsURLConnection connection = null;
		InputStream inputStream = null;
		InputStreamReader inputStreamReader = null;
		OutputStream outputStream = null;
		BufferedReader buffer = null;
		String input = null;
		try {
			url = new URL("https://www.google.com:443");
			factory = new CustomizedSSLSocketFactory();

			SSLSocket sslsocket = (SSLSocket)factory.createSocket("www.google.com", 443);
			printSocketInfo(sslsocket);

			sslsocket.startHandshake();

			inputStream = sslsocket.getInputStream();
			outputStream = sslsocket.getOutputStream();

			// Write a test byte to get a reaction :)
			outputStream.write(1);

			inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			buffer = new BufferedReader(inputStreamReader);

			while ((input = buffer.readLine()) != null) {
				System.out.print(input);
			}
			System.out.println("Successfully connected\n");

			/*connection = (HttpsURLConnection)url.openConnection();
			connection.setSSLSocketFactory(factory);
			connection.setRequestProperty("charset", "utf-8");
			inputStream = connection.getInputStream();
			inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			
			buffer = new BufferedReader(inputStreamReader);
			
			System.out.println("Response Body: ");
			while ((input = buffer.readLine()) != null) {
				System.out.println(input);
			}*/
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (KeyManagementException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (buffer != null) {
				try {
					buffer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (inputStreamReader != null) {
				try {
					inputStreamReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	@Test
	public void testUsingSSLSocket() {
		try {
			SSLSocketFactory sslsocketfactory = (SSLSocketFactory)SSLSocketFactory.getDefault();
			SSLSocket sslsocket = (SSLSocket)sslsocketfactory.createSocket("www.google.com", 443);
			printSocketInfo(sslsocket);

			sslsocket.startHandshake();

			InputStream in = sslsocket.getInputStream();
			OutputStream out = sslsocket.getOutputStream();

			// Write a test byte to get a reaction :)
			out.write(1);

			while (in.available() > 0) {
				System.out.print(in.read());
			}
			System.out.println("Successfully connected");

		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	@Test
	public void testOverHttp() {
		String httpUrl = "http://www.google.com";
		URL url;
		try {
			url = new URL(httpUrl);
			HttpURLConnection con = (HttpURLConnection)url.openConnection();

			printHttpResponse(con);

			if (con != null) {
				con.disconnect();
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testOverHttps() {
		String httpsUrl = "https://www.google.com";

		URL url;
		try {

			url = new URL(httpsUrl);
			HttpsURLConnection con = (HttpsURLConnection)url.openConnection();

			//dumpl all cert info
			printHttpsCert(con);

			//dump all the content
			printContent(con);

			if (con != null) {
				con.disconnect();
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void printSocketInfo(SSLSocket s) {
		System.out.println("Socket class: " + s.getClass());
		System.out.println("   Remote address = "
			+ s.getInetAddress().toString());
		System.out.println("   Remote port = " + s.getPort());
		System.out.println("   Local socket address = "
			+ s.getLocalSocketAddress().toString());
		System.out.println("   Local address = "
			+ s.getLocalAddress().toString());
		System.out.println("   Local port = " + s.getLocalPort());
		System.out.println("   Need client authentication = "
			+ s.getNeedClientAuth());
		SSLSession ss = s.getSession();
		System.out.println("   Cipher suite = " + ss.getCipherSuite());
		System.out.println("   Protocol = " + ss.getProtocol());
	}

	private void printHttpResponse(HttpURLConnection con) {
		if (con != null) {
			try {
				System.out.println("Response Code : " + con.getResponseCode());
				System.out.println("\n");

				System.out.println("****** Content of the URL ********");
				BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));

				String input;

				while ((input = br.readLine()) != null) {
					System.out.println(input);
				}
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void printHttpsCert(HttpsURLConnection con) {

		if (con != null) {

			try {

				System.out.println("Response Code : " + con.getResponseCode());
				System.out.println("Cipher Suite : " + con.getCipherSuite());
				System.out.println("\n");

				Certificate[] certs = con.getServerCertificates();
				for (Certificate cert : certs) {
					System.out.println("Cert Type : " + cert.getType());
					System.out.println("Cert Hash Code : " + cert.hashCode());
					System.out.println("Cert Public Key Algorithm : "
						+ cert.getPublicKey().getAlgorithm());
					System.out.println("Cert Public Key Format : "
						+ cert.getPublicKey().getFormat());
					System.out.println("\n");
				}

			} catch (SSLPeerUnverifiedException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}

	private void printContent(HttpsURLConnection con) {
		if (con != null) {

			try {

				System.out.println("****** Content of the URL ********");
				BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));

				String input;

				while ((input = br.readLine()) != null) {
					System.out.println(input);
				}
				br.close();

			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}

}
