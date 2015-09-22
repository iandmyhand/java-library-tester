package study.hard.javalib.nativelib;

import org.junit.Test;

public class ObjectTest {

	@Test
	public void test() {
		Object object = new ExtendedObject();
		System.out.println("Result of getClass(): " + object.getClass());
	}

	public class ExtendedObject extends Object {

	}

}
