package tests;

import static org.junit.Assert.assertEquals;
import ics314.FreeTimeCalculator;
import ics314.ICSEvent;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

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
	@Test
	public void constructorTest1() {
		ICSEvent event = new ICSEvent("20150217T200000Z", "20150218T220000Z");
		Calendar test_cal1 = new GregorianCalendar(2015, 1, 17, 20, 0, 0);
		Calendar test_cal2 = new GregorianCalendar(2015, 1, 18, 22, 0, 0);
		assertEquals(test_cal1, event.start);
		assertEquals(test_cal2, event.end);
	}
	
	@Test
	public void constructorTest2() {
		ICSEvent event = new ICSEvent("20151200T000000Z", "20151200T235959Z");
		Calendar test_cal1 = new GregorianCalendar(2015, 11, 0, 0, 0, 0);
		Calendar test_cal2 = new GregorianCalendar(2015, 11, 0, 23, 59, 59);
		assertEquals(test_cal1, event.start);
		assertEquals(test_cal2, event.end);
	}
	
	@Test
	public void constructorTest3() {
		ICSEvent event = new ICSEvent("00000000T000000Z", "00000000T000000Z");
		Calendar test_cal1 = new GregorianCalendar(0000, -1, 0, 0, 0, 0);
		Calendar test_cal2 = new GregorianCalendar(0000, -1, 0, 0, 0, 0);
		assertEquals(test_cal1, event.start);
		assertEquals(test_cal2, event.end);
	}
		
	@Test
	public void ICSEventConvertICSDateToCaltest2() {
	  String icsDate = "20150217T200000Z";
	  Calendar testCal1 = new GregorianCalendar(2015,1,17,20,0,0);
	  assertEquals(testCal1, ICSEvent.convertICSDateToCal(icsDate));
	}
	
	@Test
	public void ICSEventConvertICSDateToCaltest3() {
		  String icsDate = "20151225T230000Z";
		  Calendar testCal1 = new GregorianCalendar(2015,11,25,23,0,0);
		  assertEquals(testCal1, ICSEvent.convertICSDateToCal(icsDate));
		 }
	
	@Test
	public void ICSEventConvertICSDateToCaltest4() {
		  String icsDate = "20160101T210000Z";
		  Calendar testCal1 = new GregorianCalendar(2016,0,01,21,0,0);
		  assertEquals(testCal1, ICSEvent.convertICSDateToCal(icsDate));
		 }
	
	@Test
	public void AlternateTimeTestEventPartialOverlap(){
		Calendar event1Start =   new GregorianCalendar(2015, 3, 1, 5, 0, 0);
		Calendar event1End = new GregorianCalendar(2015, 3, 1, 10, 0, 0); //Apr 2
		
		Calendar event2Start =   new GregorianCalendar(2015, 3, 1, 8, 0, 0);
		Calendar event2End = new GregorianCalendar(2015, 3, 1, 15, 0, 0); //Apr 2

		
		ICSEvent e1 = new ICSEvent(event1Start, event1End);
		ICSEvent e2 = new ICSEvent(event2Start, event2End);
		
		List<ICSEvent> input = new ArrayList<ICSEvent>();
		input.add(e1);
		input.add(e2);
		
		List<ICSEvent> alternateTimes = FreeTimeCalculator.freeTime(input);
		
		ICSEvent o1 = alternateTimes.remove(0);
		assertEquals(o1.toString(), "Wed Apr 01 00:00:00 HST 2015 - Wed Apr 01 05:00:00 HST 2015");
		ICSEvent o2 = alternateTimes.remove(0);
		assertEquals(o2.toString(), "Wed Apr 01 15:00:00 HST 2015 - Wed Apr 01 23:59:59 HST 2015");

	}
	
}
