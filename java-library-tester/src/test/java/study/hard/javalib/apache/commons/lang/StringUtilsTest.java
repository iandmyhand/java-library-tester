package study.hard.javalib.apache.commons.lang;

import static org.junit.Assert.*;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Test Apache Commons Lang's StringUtils.
 * @author iandmyhand@gmail.com
 */
public class StringUtilsTest {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Test
	public void findNthOccurrenceOfCharacterInString() {
		int index = StringUtils.ordinalIndexOf("aaabaaabaaa", "b", 2);
		assertEquals(7, index);
		logger.debug("index: {}", index);
	}

	@Test
	public void findNthOccurrenceOfCharacterInString2() {
		String ip = "123.456.789.012";
		int index = StringUtils.ordinalIndexOf(ip, ".", 2);
		assertEquals(7, index);
		logger.debug("index: {}", index);
		ip = ip.substring(0, StringUtils.ordinalIndexOf(ip, ".", 2));
		assertEquals("123.456", ip);
	}

}
