package study.hard.javalib.encodingstring;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import study.hard.javalib.lang.URLUtils;

public class EncodingStringTest {

	private Logger logger = LoggerFactory.getLogger(EncodingStringTest.class);

	private static final String encodedUrl = "%ED%85%8C%EC%8A%A4%ED%8A%B8%EC%9E%85%EB%8B%88%EB%8B%A4."; // 테스트입니다.

	@Test
	public void testEncodeRelatedToKorean() {
		String tmpString = "테스트";
		System.out.println("UTF-8: " + convertEncoding(tmpString, "UTF-8"));
		System.out.println("EUC-KR: " + convertEncoding(tmpString, "EUC-KR"));
		System.out.println("MS949: " + convertEncoding(tmpString, "MS949"));
		System.out.println("CP933: " + convertEncoding(tmpString, "CP933"));

		testEncoding(tmpString);
	}

	@Test
	public void testDecodingURL() throws UnsupportedEncodingException {
		String result = java.net.URLDecoder.decode(encodedUrl, "UTF-8");
		System.out.println("result: " + result);
	}

	@Test
	public void testContainsURLEncodedPart() {
		assertTrue(URLUtils.containsURLEncodedPart(encodedUrl));
		assertTrue(URLUtils.containsURLEncodedPart(encodedUrl + "테스트"));

		assertFalse(URLUtils.containsURLEncodedPart("테스트"));
		assertFalse(URLUtils.containsURLEncodedPart(null));
	}

	private String convertEncoding(String str, String encoding) {
		ByteArrayOutputStream requestOutputStream = new ByteArrayOutputStream();
		String result = "";
		try {
			requestOutputStream.write(str.getBytes(encoding));
			result = requestOutputStream.toString(encoding);
		} catch (UnsupportedEncodingException e) {
			logger.warn("Failed to encode string to {}. {}", encoding, e);
			result = str;
		} catch (IOException e) {
			logger.warn("Failed to encode string to {}. {}", encoding, e);
			result = str;
		}
		return result;
	}

	private void testEncoding(String s) {
		try {
			String word = s;
			System.out.println("utf-8(1) : " + new String(word.getBytes("utf-8"), "euc-kr"));
			System.out.println("utf-8(2) : " + new String(word.getBytes("utf-8"), "ksc5601"));
			System.out.println("utf-8(3) : " + new String(word.getBytes("utf-8"), "x-windows-949"));
			System.out.println("utf-8(4) : " + new String(word.getBytes("utf-8"), "iso-8859-1"));

			System.out.println("iso-8859-1(1) : " + new String(word.getBytes("iso-8859-1"), "euc-kr"));
			System.out.println("iso-8859-1(2) : " + new String(word.getBytes("iso-8859-1"), "ksc5601"));
			System.out.println("iso-8859-1(3) : " + new String(word.getBytes("iso-8859-1"), "x-windows-949"));
			System.out.println("iso-8859-1(4) : " + new String(word.getBytes("iso-8859-1"), "utf-8"));

			System.out.println("euc-kr(1) : " + new String(word.getBytes("euc-kr"), "ksc5601"));
			System.out.println("euc-kr(2) : " + new String(word.getBytes("euc-kr"), "utf-8"));
			System.out.println("euc-kr(3) : " + new String(word.getBytes("euc-kr"), "x-windows-949"));
			System.out.println("euc-kr(4) : " + new String(word.getBytes("euc-kr"), "iso-8859-1"));

			System.out.println("ksc5601(1) : " + new String(word.getBytes("ksc5601"), "euc-kr"));
			System.out.println("ksc5601(2) : " + new String(word.getBytes("ksc5601"), "utf-8"));
			System.out.println("ksc5601(3) : " + new String(word.getBytes("ksc5601"), "x-windows-949"));
			System.out.println("ksc5601(4) : " + new String(word.getBytes("ksc5601"), "iso-8859-1"));

			System.out.println("x-windows-949(1) : " + new String(word.getBytes("x-windows-949"), "euc-kr"));
			System.out.println("x-windows-949(2) : " + new String(word.getBytes("x-windows-949"), "utf-8"));
			System.out.println("x-windows-949(3) : " + new String(word.getBytes("x-windows-949"), "ksc5601"));
			System.out.println("x-windows-949(4) : " + new String(word.getBytes("x-windows-949"), "iso-8859-1"));
		} catch (UnsupportedEncodingException e) {
			logger.warn("Failed to encode string. {}", e);
		}
	}

}
