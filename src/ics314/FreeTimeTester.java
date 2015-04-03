package ics314;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Test;

public class FreeTimeTester {

	@Test
	public void test() {
		FreeTimeCalculator ftc = new FreeTimeCalculator();
		int a = 3;
		int b = 7;
		int c = ftc.add(a, b);
		assertEquals(10, c);
	}

	@Test
	public void test2() {
		Calendar testcal = ICSEvent.convertICSDateToCal("20120517T000000Z");
		int year = testcal.get(Calendar.YEAR);
		assertEquals(year, 2012);
	}
	
	@Test
	public void test3() {
		Calendar testcal = ICSEvent.convertICSDateToCal("20120517T000000Z");
		int month = testcal.get(Calendar.MONTH);
		assertEquals(month, 04);
	}
	
	@Test
	public void test4() {
		Calendar testcal = ICSEvent.convertICSDateToCal("20120517T000000Z");
		int day = testcal.get(Calendar.DATE);
		assertEquals(day, 17);
	}
	
	@Test
	public void test5() {
		Calendar testcal = ICSEvent.convertICSDateToCal("20120100T000000Z");
		int hour = testcal.get(Calendar.HOUR_OF_DAY);
		assertEquals(hour, 00);
	}
	
	@Test
	public void test6() {
		Calendar testcal = ICSEvent.convertICSDateToCal("20120100T000000Z");
		int minute = testcal.get(Calendar.MINUTE);
		assertEquals(minute, 00);
	}
	
	@Test
	public void test7() {
		Calendar testcal = ICSEvent.convertICSDateToCal("20120100T000000Z");
		int second = testcal.get(Calendar.SECOND);
		assertEquals(second, 00);
	}
}
