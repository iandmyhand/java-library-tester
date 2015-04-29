package study.hard.javalib.nativelib;

import org.junit.Test;

public class LanguageTest {

	@Test
	public void testStaticVariable() {
		ClassThatHasStaticVariable a1 = new ClassThatHasStaticVariable();
		ClassThatHasStaticVariable a2 = new ClassThatHasStaticVariable();
	}

}

class ClassThatHasStaticVariable {
	static int count = 0;

	ClassThatHasStaticVariable() {
		count++;
		System.out.print(count);
	}
}