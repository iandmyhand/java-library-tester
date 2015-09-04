package study.hard.javalib.nativelib;

import java.math.BigDecimal;

import org.junit.Test;

public class BicDecimal {

	@Test
	public void testToDevide() {
		BigDecimal a = new BigDecimal(110000);
		BigDecimal b = new BigDecimal(1.1);
		System.out.println(a.divide(b, 3, BigDecimal.ROUND_UP)); // 100000.000
		System.out.println(a.divide(b, 3, BigDecimal.ROUND_DOWN)); // 99999.999
	}

}
