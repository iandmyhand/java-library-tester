package study.hard.javalib.jodatime;

import static org.junit.Assert.*;

import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.Period;
import org.joda.time.format.DateTimeFormat;
import org.junit.Before;
import org.junit.Test;

public class JodaTimeTest {

	DateTime start;
	DateTime end;

	@Before
	public void setUp() {
		start = new DateTime(2014, 9, 25, 14, 21, 24, 0);
		end = new DateTime(2015, 1, 1, 14, 22, 34, 0);
	}

	@Test
	public void testInitializeWithPattern() {
		DateTime someday = DateTime.parse("201501", DateTimeFormat.forPattern("yyyyMM"));
		assertEquals(new DateTime(2015, 1, 1, 0, 0), someday);
	}

	@Test
	public void testInterval() {
		start = new DateTime(2004, 12, 25, 0, 0, 0, 0);
		end = new DateTime(2005, 1, 1, 0, 0, 0, 0);

		Interval interval = new Interval(start, end);
		Period period = interval.toPeriod();

		assertEquals(0, period.getMonths());
		assertEquals(1, period.getWeeks());
		assertEquals(0, period.getDays());
	}

	@Test
	public void testToStringWithPattern() {
		assertEquals("2014/09/25 14:21:24", start.toString("yyyy/MM/dd HH:mm:ss"));
	}

	@Test
	public void testAdditionAndSubtraction() {
		end = end.minusMonths(1);
		assertEquals(new DateTime(2014, 12, 1, 14, 22, 34, 0), end);

		end = end.plusHours(10);
		assertEquals(new DateTime(2014, 12, 2, 0, 22, 34, 0), end);
	}

}
