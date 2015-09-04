package study.hard.javalib.service;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class Calculator {
	public static Double devideByDecimalPoint(int intVlaue, double doubleValue) {

		BigDecimal intValueDecimal = new BigDecimal(intVlaue);
		System.out.println("intValueDecimal: " + intValueDecimal);

		DecimalFormat df = new DecimalFormat("#.#");
		String doubleValueString = df.format(doubleValue);
		System.out.println("doubleValueString: " + doubleValueString);
		BigDecimal dividorDecimal = new BigDecimal(doubleValueString);
		System.out.println("dividorDecimal: " + dividorDecimal);

		//		BigDecimal taxableDecimal = intValueDecimal.divide(dividorDecimal, BigDecimal.ROUND_FLOOR); // for balancing accounts
		BigDecimal taxableDecimal = intValueDecimal.divide(dividorDecimal);
		System.out.println("taxableDecimal: " + taxableDecimal);

		return taxableDecimal.doubleValue();
	}
}
