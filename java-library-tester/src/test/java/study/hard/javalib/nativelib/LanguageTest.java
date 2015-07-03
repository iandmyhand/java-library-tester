package study.hard.javalib.nativelib;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import study.hard.javalib.commons.entity.BasisObject;

public class LanguageTest {

	private static Logger logger = LoggerFactory.getLogger(LanguageTest.class);

	@Test
	public void testStaticVariable() {
		ClassThatHasStaticVariable a1 = new ClassThatHasStaticVariable();
		logger.debug("a1: " + a1);
		ClassThatHasStaticVariable a2 = new ClassThatHasStaticVariable();
		logger.debug("a2: " + a2);
	}

	private static class ClassThatHasStaticVariable extends BasisObject {
		private static final long serialVersionUID = -8833377195166280242L;

		static int count = 0;

		ClassThatHasStaticVariable() {
			count++;
			logger.debug("count: " + count);
		}
	}
}
