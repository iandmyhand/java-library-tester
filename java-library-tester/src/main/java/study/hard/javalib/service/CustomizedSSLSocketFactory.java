package study.hard.javalib.service;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

public class CustomizedSSLSocketFactory extends SSLSocketFactory {

	private SSLContext _ctx;

	private String[] _ciphers;
	private String[] _protocols;

	public CustomizedSSLSocketFactory() throws NoSuchAlgorithmException, KeyManagementException {
		initCustomizedSSLSocketFactory(null, null, null);
	}

	public CustomizedSSLSocketFactory(KeyManager[] km, TrustManager[] tm, SecureRandom random) throws NoSuchAlgorithmException, KeyManagementException {
		initCustomizedSSLSocketFactory(km, tm, random);
	}

	public CustomizedSSLSocketFactory(SSLContext ctx) throws NoSuchAlgorithmException, KeyManagementException {
		initSSLSocketFactoryEx(ctx);
	}

	@Override
	public String[] getDefaultCipherSuites() {
		return _ciphers;
	}

	@Override
	public String[] getSupportedCipherSuites() {
		return _ciphers;
	}

	public String[] getDefaultProtocols() {
		return _protocols;
	}

	public String[] getSupportedProtocols() {
		return _protocols;
	}

	@Override
	public Socket createSocket(Socket s, String host, int port, boolean autoClose) throws IOException {
		SSLSocketFactory factory = _ctx.getSocketFactory();
		SSLSocket ss = (SSLSocket)factory.createSocket(s, host, port, autoClose);

		ss.setEnabledProtocols(_protocols);
		ss.setEnabledCipherSuites(_ciphers);

		return ss;
	}

	@Override
	public Socket createSocket(InetAddress address, int port, InetAddress localAddress, int localPort) throws IOException {
		SSLSocketFactory factory = _ctx.getSocketFactory();
		SSLSocket ss = (SSLSocket)factory.createSocket(address, port, localAddress, localPort);

		ss.setEnabledProtocols(_protocols);
		ss.setEnabledCipherSuites(_ciphers);

		return ss;
	}

	@Override
	public Socket createSocket(String host, int port, InetAddress localHost, int localPort) throws IOException {
		SSLSocketFactory factory = _ctx.getSocketFactory();
		SSLSocket ss = (SSLSocket)factory.createSocket(host, port, localHost, localPort);

		ss.setEnabledProtocols(_protocols);
		ss.setEnabledCipherSuites(_ciphers);

		return ss;
	}

	@Override
	public Socket createSocket(InetAddress host, int port) throws IOException {
		SSLSocketFactory factory = _ctx.getSocketFactory();
		SSLSocket ss = (SSLSocket)factory.createSocket(host, port);

		ss.setEnabledProtocols(_protocols);
		ss.setEnabledCipherSuites(_ciphers);

		return ss;
	}

	@Override
	public Socket createSocket(String host, int port) throws IOException {
		SSLSocketFactory factory = _ctx.getSocketFactory();
		SSLSocket ss = (SSLSocket)factory.createSocket(host, port);

		ss.setEnabledProtocols(_protocols);
		ss.setEnabledCipherSuites(_ciphers);

		return ss;
	}

	private void initCustomizedSSLSocketFactory(KeyManager[] km, TrustManager[] tm, SecureRandom random)
		throws NoSuchAlgorithmException, KeyManagementException {
		_ctx = SSLContext.getInstance("TLS"); // http://docs.oracle.com/javase/7/docs/technotes/guides/security/StandardNames.html#SSLContext
		_ctx.init(km, tm, random);

		_protocols = GetProtocolList();
		_ciphers = GetCipherList();
	}

	private void initSSLSocketFactoryEx(SSLContext ctx)
		throws NoSuchAlgorithmException, KeyManagementException {
		_ctx = ctx;

		_protocols = GetProtocolList();
		_ciphers = GetCipherList();
	}

	protected String[] GetProtocolList() {
		String[] preferredProtocols = {"TLSv1", "TLSv1.1", "TLSv1.2", "TLSv1.3"};
		String[] availableProtocols = null;

		SSLSocket socket = null;

		try {
			SSLSocketFactory factory = _ctx.getSocketFactory();
			socket = (SSLSocket)factory.createSocket();

			availableProtocols = socket.getSupportedProtocols();
			Arrays.sort(availableProtocols);
		} catch (Exception e) {
			return new String[] {"TLSv1"};
		} finally {
			if (socket != null)
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}

		List<String> aa = new ArrayList<String>();
		for (int i = 0; i < preferredProtocols.length; i++) {
			int idx = Arrays.binarySearch(availableProtocols, preferredProtocols[i]);
			if (idx >= 0)
				aa.add(preferredProtocols[i]);
		}

		return aa.toArray(new String[0]);
	}

	protected String[] GetCipherList() {
		String[] preferredCiphers = {
			//			"TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA", // I need this!! but it doesn't work...

			// *_CHACHA20_POLY1305 are 3x to 4x faster than existing cipher suites.
			//   http://googleonlinesecurity.blogspot.com/2014/04/speeding-up-and-strengthening-https.html
			// Use them if available. Normative names can be found at (TLS spec depends on IPSec spec):
			//   http://tools.ietf.org/html/draft-nir-ipsecme-chacha20-poly1305-01
			//   http://tools.ietf.org/html/draft-mavrogiannopoulos-chacha-tls-02
			"TLS_ECDHE_ECDSA_WITH_CHACHA20_POLY1305",
			"TLS_ECDHE_RSA_WITH_CHACHA20_POLY1305",
			"TLS_ECDHE_ECDSA_WITH_CHACHA20_SHA",
			"TLS_ECDHE_RSA_WITH_CHACHA20_SHA",

			"TLS_DHE_RSA_WITH_CHACHA20_POLY1305",
			"TLS_RSA_WITH_CHACHA20_POLY1305",
			"TLS_DHE_RSA_WITH_CHACHA20_SHA",
			"TLS_RSA_WITH_CHACHA20_SHA",

			// Done with bleeding edge, back to TLS v1.2 and below
			//			"TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA384",
			//			"TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA384",
			//			"TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA256",
			//			"TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256",

			//			"TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384",
			//			"TLS_DHE_DSS_WITH_AES_256_GCM_SHA384",
			//			"TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256",
			//			"TLS_DHE_DSS_WITH_AES_128_GCM_SHA256",

			// TLS v1.0 (with some SSLv3 interop)
			"TLS_DHE_RSA_WITH_AES_256_CBC_SHA384",
			"TLS_DHE_DSS_WITH_AES_256_CBC_SHA256",
			"TLS_DHE_RSA_WITH_AES_128_CBC_SHA",
			"TLS_DHE_DSS_WITH_AES_128_CBC_SHA",

			//			"TLS_DHE_RSA_WITH_3DES_EDE_CBC_SHA",
			//			"TLS_DHE_DSS_WITH_3DES_EDE_CBC_SHA",
			//			"SSL_DH_RSA_WITH_3DES_EDE_CBC_SHA",
			//			"SSL_DH_DSS_WITH_3DES_EDE_CBC_SHA",

			// RSA key transport sucks, but they are needed as a fallback.
			// For example, microsoft.com fails under all versions of TLS
			// if they are not included. If only TLS 1.0 is available at
			// the client, then google.com will fail too. TLS v1.3 is
			// trying to deprecate them, so it will be interesteng to see
			// what happens.
			//			"TLS_RSA_WITH_AES_256_CBC_SHA256",
			//			"TLS_RSA_WITH_AES_256_CBC_SHA",
			//			"TLS_RSA_WITH_AES_128_CBC_SHA256",
			//			"TLS_RSA_WITH_AES_128_CBC_SHA"
		};

		String[] availableCiphers = null;

		try {
			SSLSocketFactory factory = _ctx.getSocketFactory();
			availableCiphers = factory.getSupportedCipherSuites();
			Arrays.sort(availableCiphers);
			for (int i = 0; i < availableCiphers.length; i++) {
				System.out.println("availableCiphers[" + i + "]: " + availableCiphers[i]);
			}
			for (int i = 0; i < preferredCiphers.length; i++) {
				System.out.println("preferredCiphers[" + i + "]: " + preferredCiphers[i]);
			}
		} catch (Exception e) {
			return new String[] {
				"TLS_DHE_DSS_WITH_AES_128_CBC_SHA",
				"TLS_DHE_DSS_WITH_AES_256_CBC_SHA",
				"TLS_DHE_RSA_WITH_AES_128_CBC_SHA",
				"TLS_DHE_RSA_WITH_AES_256_CBC_SHA",
				"TLS_RSA_WITH_AES_256_CBC_SHA256",
				"TLS_RSA_WITH_AES_256_CBC_SHA",
				"TLS_RSA_WITH_AES_128_CBC_SHA256",
				"TLS_RSA_WITH_AES_128_CBC_SHA",
				"TLS_EMPTY_RENEGOTIATION_INFO_SCSV"
			};
		}

		List<String> aa = new ArrayList<String>();
		for (int i = 0; i < preferredCiphers.length; i++) {
			int idx = Arrays.binarySearch(availableCiphers, preferredCiphers[i]);
			if (idx >= 0)
				aa.add(preferredCiphers[i]);
		}

		aa.add("TLS_EMPTY_RENEGOTIATION_INFO_SCSV");

		return aa.toArray(new String[0]);
	}

}
