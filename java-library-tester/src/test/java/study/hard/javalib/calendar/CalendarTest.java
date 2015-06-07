package study.hard.javalib.calendar;

import static org.junit.Assert.*;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang.time.DateUtils;
import org.junit.Before;
import org.junit.Test;

public class CalendarTest {

	private Date now;
	private Date fromDate;
	private Date toDate;

	@Before
	public void setUp() {
		now = new Date();
		fromDate = DateUtils.addMonths(now, -1);
		toDate = now;
	}

	@Test
	public void testBefore() {
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

	@Test
	public void testCompareTo() {
		Calendar fromCalendar = DateUtils.toCalendar(fromDate);
		Calendar toCalendar = DateUtils.toCalendar(toDate);
		assertEquals(-1, fromCalendar.compareTo(toCalendar));
		assertEquals(1, toCalendar.compareTo(fromCalendar));

		fromCalendar.add(Calendar.MONTH, 1);
		assertEquals(0, toCalendar.compareTo(fromCalendar));
	}

	@Test
	public void testCompareToOfDate() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMM", Locale.getDefault());
		ParsePosition pos = new ParsePosition(0);
		Date startDate = formatter.parse("201410", pos);
		pos.setIndex(0);
		Date endDate = formatter.parse("201501", pos);

		int i = 0;
		Date targetDate = endDate;
		while (startDate.compareTo(targetDate) <= 0) {
			i++;
			targetDate = DateUtils.addMonths(targetDate, -1);
		}
		assertEquals(4, i);
	}

}
