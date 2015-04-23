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

	@Test
	public void testSplitNewLine() {
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

}
