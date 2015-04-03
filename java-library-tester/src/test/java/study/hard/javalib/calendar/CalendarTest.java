package study.hard.javalib.calendar;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.time.DateUtils;
import org.junit.Test;

public class CalendarTest {

	@Test
	public void testBefore() {
		Date now = new Date();
		Date fromDate = DateUtils.addMonths(now, -1);
		Date toDate = now;

		Date truncatedFromDate = DateUtils.truncate(fromDate, Calendar.DATE);
		Date truncatedToDate = DateUtils.truncate(toDate, Calendar.DATE);

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(truncatedFromDate);
		calendar.add(Calendar.MONTH, 1);

		Boolean before;
		before = calendar.getTime().before(truncatedToDate);
		System.out.println(truncatedFromDate);
		System.out.println(truncatedToDate);
		System.out.println(before);

		truncatedToDate = DateUtils.truncate(DateUtils.addDays(toDate, -2), Calendar.DATE);
		before = calendar.getTime().before(truncatedToDate);
		System.out.println(truncatedFromDate);
		System.out.println(truncatedToDate);
		System.out.println(before);
	}

	@Test
	public void testBefore2() {
		Date now = new Date();
		Date fromDate = DateUtils.addMonths(now, -1);
		Date toDate = now;

		Date truncatedFromDate = DateUtils.truncate(fromDate, Calendar.DATE);
		Date truncatedToDate = DateUtils.truncate(toDate, Calendar.DATE);

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(truncatedFromDate);
		calendar.add(Calendar.MONTH, 1);

		Boolean before;
		before = calendar.before(truncatedToDate);
		System.out.println(truncatedFromDate);
		System.out.println(truncatedToDate);
		System.out.println(before);

		truncatedToDate = DateUtils.truncate(DateUtils.addDays(toDate, 4), Calendar.DATE);
		before = calendar.before(truncatedToDate);
		System.out.println(truncatedFromDate);
		System.out.println(truncatedToDate);
		System.out.println(before);
	}

}
