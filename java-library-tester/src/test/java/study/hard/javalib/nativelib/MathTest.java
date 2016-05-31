package study.hard.javalib.nativelib;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.TestPropertySource;

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
	public void divide() {
		int a = 29;
		int b = 30;
		System.out.println(String.format("%d / %d == %d", a, b, (a / b)));
        assertEquals(0, (a / b));
        assertEquals(0, (1 / 30));
        assertEquals(0, (0 / 30));
        assertEquals(1, (30 / 30));
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
	public void floor2() {
		double maxBytes = 500D;
		double hangulBytesPerOneCharacter = 3D;
		String sampleHangulString = "한글 또는 조선글은 1443년 조선 제4대 임금 세종이 훈민정음(訓民正音)이라는 이름으로 창제하여 1446년에 반포한 문자로, 한국어를 표기하기 위해 만들어졌다.[1][2] 이후 한문을 고수하는 사대부들에게는 경시되기도 하였으나, 조선 왕실과 일부 양반층과 서민층을 중심으로 이어지다가 1894년 갑오개혁에서 한국의 공식적인 나라 글자가 되었고, 1910년대에 이르러 한글학자인 주시경이 '한글'이라는 이름을 사용하였다. 갈래는 표음 문자 가운데 음소 문자에 속한다. 한국에서는 한글전용법이 시행되고 있다.";
		System.out.println("max length: " + ((int)Math.floor((double)maxBytes / (double)hangulBytesPerOneCharacter)) + "\n");
		System.out.println("original string: [" + sampleHangulString + "]");
		System.out.println("length of original string: " + sampleHangulString.length() + "\n");
		sampleHangulString = sampleHangulString.substring(0, ((int)Math.floor((double)maxBytes / (double)hangulBytesPerOneCharacter)));
		System.out.println("sampleMessage: [" + sampleHangulString + "]");
		System.out.println("length of cut string: " + sampleHangulString.length());
	}

	@Test
	public void ceil() {
		logger.debug("(3 / 7): " + (3 / 7)); // 0
		logger.debug("(3D / 7D): " + (3D / 7D)); // 0.42857142857142855
		logger.debug("Math.ceil(3 / 7): " + Math.ceil(3 / 7)); // 0.0
		logger.debug("Math.ceil(3D / 7D): " + Math.ceil(3D / 7D)); // 1.0
		logger.debug("(int)Math.ceil(3 / 7): " + (int)Math.ceil(3 / 7)); // 0
		logger.debug("(int)Math.ceil(3D / 7D): " + (int)Math.ceil(3D / 7D)); // 1
		logger.debug("(int)Math.ceil((double)3 / (double)7): " + (int)Math.ceil((double)3 / (double)7)); // 1
	}

}
