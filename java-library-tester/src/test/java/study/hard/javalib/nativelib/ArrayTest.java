package study.hard.javalib.nativelib;

import org.junit.Before;
import org.junit.Test;

public class ArrayTest {

	Integer[] intArray = new Integer[11];

	@Before
	public void setUp() {
		for (int i = 0; i < intArray.length; i++) {
			intArray[i] = (int)(Math.random() * 100);
		}

		for (Integer num : intArray) {
			System.out.print(num + ", ");
		}
	}

	@Test
	public void findTheAverageOfNumbersInArray() {
		Integer sum = 0;
		for (Integer num : intArray) {
			sum += num;
		}
		System.out.println("sum: " + sum);

		System.out.print("average: ");
		System.out.println(sum.floatValue() / intArray.length);
	}
}
