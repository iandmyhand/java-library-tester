package study.hard.slf4j;

import org.apache.log4j.PropertyConfigurator;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author SeomGi, Han(iandmyhand@gmail.com)
 */
public class Slf4jTester {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Before
	public void setUp() {
		PropertyConfigurator.configure("src/test/resources/log4j.properties");
	}

	@Test
	public void test() {
		logger.debug("This is test message.");
	}

}
