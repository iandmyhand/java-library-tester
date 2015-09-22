package study.hard.javalib.nativelib;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MathTest {

	private static Logger logger = LoggerFactory.getLogger(MathTest.class);

	private Double amount;
	private Double ratio;
	private Double result;

	@Before
	public void setUp() {
		amount = 0D;
		ratio = 0D;
		result = 0D;
		logger.debug("amount: {} / ratio: {} / result: {}", new Object[] {amount, ratio, result});
	}

	@Test
	public void floor() {
		amount = 39000.000D;
		ratio = 0.020D;
		result = Math.floor(amount * ratio);

		logger.debug("result: {}", result);

		assertEquals(new Double(780D), result);
	}

	@Test
	public void ceil() {
		logger.debug("(3 / 7): " + (3 / 7));
		logger.debug("(3D / 7D): " + (3D / 7D));
		logger.debug("Math.ceil(3 / 7): " + Math.ceil(3 / 7));
		logger.debug("Math.ceil(3D / 7D): " + Math.ceil(3D / 7D));
		logger.debug("(int)Math.ceil(3 / 7): " + (int)Math.ceil(3 / 7));
		logger.debug("(int)Math.ceil(3D / 7D): " + (int)Math.ceil(3D / 7D));
		logger.debug("(int)Math.ceil((double)3 / (double)7): " + (int)Math.ceil((double)3 / (double)7));
	}

}
