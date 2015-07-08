package study.hard.javalib.nativelib;

import org.junit.Test;

import study.hard.javalib.exception.BaseException;
import study.hard.javalib.exception.InvalidParameterException;

public class ExceptionTest {

	@Test
	public void testCustomException() {
		try {
			throw new InvalidParameterException("TEST EXCEPTION", "TEST EXCEPTION PARAMS IN CONSTRUCTOR");
		} catch (BaseException e) {
			e.printErrorLog("TEST EXCEPTION PARAMS");
		}
	}

}
