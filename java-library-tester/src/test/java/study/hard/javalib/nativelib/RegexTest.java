package study.hard.javalib.nativelib;

import static org.junit.Assert.*;

import java.util.List;
import java.util.regex.Pattern;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;

public class RegexTest {

	static final Logger logger = LoggerFactory.getLogger(RegexTest.class);

	static final Pattern IP_PATTERN_WITH_SUBNET = Pattern.compile("^([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])(\\.([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])){0,3}$");

	@Test
	public void testRegexForIPWithSubnet() {
		List<String> ipList = Lists.newArrayList();
		ipList.add("0");
		ipList.add("0.0");
		ipList.add("0.0.0");
		ipList.add("0.0.0.0");
		ipList.add("255");
		ipList.add("255.255");
		ipList.add("255.255.255");
		ipList.add("255.255.255.255");
		ipList.add("100");
		ipList.add("100.100");
		ipList.add("100.100.100");
		ipList.add("100.100.100.100");
		ipList.add("192.168.1.1");
		ipList.add("26.10.2.10");
		for (String ip : ipList) {
			logger.debug("ip {}: {}", ip, IP_PATTERN_WITH_SUBNET.matcher(ip).matches());
			assertTrue(IP_PATTERN_WITH_SUBNET.matcher(ip).matches());
		}
	}

	@Test
	public void testNotMatchedRegexForIPWithSubnet() {
		List<String> ipList = Lists.newArrayList();
		ipList.add("");
		ipList.add(".");
		ipList.add("0.");
		ipList.add("0.0.");
		ipList.add("0.0.0.");
		ipList.add("0.0.0.0.");
		ipList.add("01");
		ipList.add("01.01");
		ipList.add("01.01.0.011");
		ipList.add("255.");
		ipList.add("255.255.");
		ipList.add("255.255.255.");
		ipList.add("255.255.255.255.");
		ipList.add("255.255.255.255.255");
		ipList.add("100.");
		ipList.add("100.100.");
		ipList.add("100.100.100.");
		ipList.add("100.100.100.100.");
		ipList.add("ABC");
		ipList.add("abc.abc.abc.abc");
		ipList.add("267");
		ipList.add("256.256");
		ipList.add("256.256.256");
		ipList.add("256.256.256.256");
		ipList.add("10.10.10.256");
		ipList.add("2222.22.22.22");
		for (String ip : ipList) {
			logger.debug("ip {}: {}", ip, IP_PATTERN_WITH_SUBNET.matcher(ip).matches());
			assertFalse(IP_PATTERN_WITH_SUBNET.matcher(ip).matches());
		}
	}

}
