package study.hard.javalib.charset;

import static org.junit.Assert.*;

import java.nio.charset.StandardCharsets;

import org.junit.Test;

public class StandardCharsetsTest {

	@Test
	public void test() {
		System.out.println(StandardCharsets.UTF_8);
		System.out.println(StandardCharsets.UTF_8.name());
		assertNotEquals("UTF-8", StandardCharsets.UTF_8);
		assertEquals("UTF-8", StandardCharsets.UTF_8.name());
	}
}
