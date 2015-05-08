package study.hard.javalib.nativelib;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * A test case for shift operator.
 * @author iandmyhand@gmail.com
 */
public class ShiftOperatorTest {

	int i = 0;

	public void setUpTo2() {
		i = 2;
	}

	public void setUpTo8() {
		i = 8;
	}

	@Test
	public void testLeftShiftOperator() {
		System.out.println("Test left shift operator.");

		setUpTo2();
		System.out.print(i + " << 1 = ");
		i = i << 1;
		System.out.println(i + " [" + getBit(i) + "]");
		assertEquals(4, i);

		setUpTo2();
		System.out.print(i + " << 2 = ");
		i = i << 2;
		System.out.println(i + " [" + getBit(i) + "]");
		assertEquals(8, i);
	}

	@Test
	public void testRightShiftOperator() {
		System.out.println("Test right shift operator.");

		setUpTo8();
		System.out.print(i + " >> 2 = ");
		i = i >> 2;
		System.out.println(i + " [" + getBit(i) + "]");
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
