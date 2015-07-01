package study.hard.javalib.nativelib;

import java.io.UnsupportedEncodingException;

import org.junit.Test;

public class PrimitiveTypeTest {

	@Test
	public void test() throws UnsupportedEncodingException {
		getInt("A");
		getInt("Z");
	}

	private void getInt(String str) {
		char ch = str.charAt(0);
		int x = Character.getNumericValue(ch);
		System.out.println(x);
	}

}
