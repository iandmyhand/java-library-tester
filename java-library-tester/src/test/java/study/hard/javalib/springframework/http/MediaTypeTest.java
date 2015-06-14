package study.hard.javalib.springframework.http;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.http.MediaType;

public class MediaTypeTest {

	@Test
	public void test() {
		System.out.println(MediaType.APPLICATION_FORM_URLENCODED);
		System.out.println(MediaType.APPLICATION_FORM_URLENCODED_VALUE);
		assertNotEquals("application/x-www-form-urlencoded", MediaType.APPLICATION_FORM_URLENCODED);
		assertEquals("application/x-www-form-urlencoded", MediaType.APPLICATION_FORM_URLENCODED_VALUE);
	}

}
