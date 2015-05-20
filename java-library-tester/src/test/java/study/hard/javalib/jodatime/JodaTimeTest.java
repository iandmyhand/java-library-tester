package study.hard.javalib.jodatime;

import static org.junit.Assert.*;

import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.Period;
import org.junit.Test;

public class JodaTimeTest {

	@Test
	public void testInterval() {
		DateTime start = new DateTime(2004, 12, 25, 0, 0, 0, 0);
		DateTime end = new DateTime(2005, 1, 1, 0, 0, 0, 0);
		Interval interval = new Interval(start, end);
		Period period = interval.toPeriod();
		assertEquals(0, period.getMonths());
		assertEquals(1, period.getWeeks());
		assertEquals(0, period.getDays());
	}
}
