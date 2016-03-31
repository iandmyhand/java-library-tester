package study.hard.javalib.nativelib;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.google.common.collect.Lists;

public class StringTest {

	static final String STR_WITH_LF = "one\ntwo\nthree";
	static final String STR_WITH_LF_AND_EMPTYLINE = "one\ntwo\nthree\n";
	static final String STR_WITH_CRLF = "one\r\ntwo\r\nthree";
	static final String STR_WITH_CRLF_AND_EMPTYLINE = "one\r\ntwo\r\nthree\r\n\r\n";
	static final String STR_SAMPLE_HANGUL = "한글 샘플 문자열";

	@Test
	public void testToSplitNewLine() {
		List<String> strList;

		strList = Lists.newArrayList(STR_WITH_LF.split("\\r?\\n"));
		assertEquals(3, strList.size());
		System.out.println(strList);

		strList = Lists.newArrayList(STR_WITH_CRLF.split("\\r?\\n"));
		assertEquals(3, strList.size());
		System.out.println(strList);

		strList = Lists.newArrayList(STR_WITH_LF_AND_EMPTYLINE.split("\\r?\\n"));
		assertEquals(3, strList.size());
		System.out.println(strList);

		strList = Lists.newArrayList(STR_WITH_CRLF_AND_EMPTYLINE.split("\\r?\\n"));
		assertEquals(3, strList.size());
		System.out.println(strList);
	}

	@Test
	public void testSubstring() {
		String tmp = STR_SAMPLE_HANGUL;
		System.out.println("tmp: [" + tmp + "]");
		System.out.println("tmp length: " + tmp.length());
		tmp = tmp.substring(0, 5);
		System.out.println("tmp: [" + tmp + "]");
	}
	
	@Test
	public void testFormating() {
		System.out.println(String.format("A color of %s is the %s.", "Banana", "yellow"));
	}

}
