package study.hard.javalib.nativelib.system;

import org.junit.Test;

public class GetenvTest {

	@Test
	public void testGetEnv() {
		final String value = System.getenv("TEMP");
		System.out.println(value);
	}

}
