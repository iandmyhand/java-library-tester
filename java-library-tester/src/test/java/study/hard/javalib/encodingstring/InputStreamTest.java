package study.hard.javalib.encodingstring;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;

import org.apache.commons.lang.StringUtils;
import org.junit.Before;
import org.junit.Test;

public class InputStreamTest {

	private URL url;

	@Before
	public void setUp() throws MalformedURLException {
		url = new URL("http://www.google.com");
	}

	@Test
	public void testBytesRead() throws IOException {
		StringBuilder stringBuilder = new StringBuilder();
		BufferedReader bufferedReader = null;

		try {
			InputStream inputStream = url.openStream();
			if (inputStream != null) {
				bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
				char[] charBuffer = new char[1024];
				int bytesRead = -1;
				while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
					stringBuilder.append(charBuffer, 0, bytesRead);
				}
			} else {
				stringBuilder.append("");
			}
		} catch (IOException ex) {
			throw ex;
		} finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException ex) {
					throw ex;
				}
			}
		}

		assertTrue(StringUtils.isNotBlank(stringBuilder.toString()));
		System.out.println("> " + stringBuilder);
		System.out.println("length: " + stringBuilder.length());
	}

	@Test
	public void testReadLine() throws IOException {
		LinkedList<String> lines = new LinkedList<String>();
		BufferedReader bufferedReader = null;

		try {
			InputStream inputStream = url.openStream();
			if (inputStream != null) {
				bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
				String readLine;
				while ((readLine = bufferedReader.readLine()) != null) {
					lines.add(readLine);
				}
			}
		} catch (IOException ex) {
			throw ex;
		} finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException ex) {
					throw ex;
				}
			}
		}

		assertTrue(0 < lines.size());
		for (String line : lines) {
			System.out.println("> " + line);
		}
	}
}
