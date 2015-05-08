package study.hard.javalib.nativelib;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * A test case for shift operator.
 * @author iandmyhand@gmail.com
 */
public class ShiftOperatorTest {

	@Test
	public void testShiftOperator() {
		int i = 0;

		i = 2;
		System.out.print("i: " + i);
		System.out.println(" [" + getBit(i) + "]");

		i = 2;
		i = i << 1;
		System.out.print("i: " + i);
		System.out.println(" [" + getBit(i) + "]");
		assertEquals(4, i);

		i = 2;
		i = i << 2;
		System.out.print("i: " + i);
		System.out.println(" [" + getBit(i) + "]");
		assertEquals(8, i);

		i = 8;
		i = i >> 2;
		System.out.print("i: " + i);
		System.out.println(" [" + getBit(i) + "]");
		assertEquals(2, i);
	}

	String getBit(int n) {
		int[] array = new int[8];
		int iattr = (int)((byte)n & 0xFF);
		int bitWise = 1;
		for (int i = 0; i < 8; i++) {
			if ((bitWise & iattr) > 0)
				array[i] = 1;
			else
				array[i] = 0;
			bitWise = (bitWise << 1) & 0xfe;
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < array.length; ++i)
			sb.append(array[7 - i]);
		return sb.toString();
	}
}
