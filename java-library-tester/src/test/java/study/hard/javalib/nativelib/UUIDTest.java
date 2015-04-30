package study.hard.javalib.nativelib;

import java.util.UUID;

import org.junit.Test;

public class UUIDTest {

	@Test
	public void generateRandomUUID() {
		UUID uniqueKey = UUID.randomUUID();
		System.out.println(uniqueKey);
	}

}
