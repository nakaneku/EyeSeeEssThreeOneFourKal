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
		assertEquals(9, c);
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
	public void test2() {
	  String icsDate = "20150217T200000Z";
	  Calendar testCal1 = new GregorianCalendar(2015,1,17,20,0,0);
	  assertEquals(testCal1, ICSEvent.convertICSDateToCal(icsDate));
	}
	
	@Test
	public void test3() {
		  String icsDate = "20151225T230000Z";
		  Calendar testCal1 = new GregorianCalendar(2015,11,25,23,0,0);
		  assertEquals(testCal1, ICSEvent.convertICSDateToCal(icsDate));
		 }
	
	@Test
	public void test4() {
		  String icsDate = "20160101T210000Z";
		  Calendar testCal1 = new GregorianCalendar(2016,0,01,21,0,0);
		  assertEquals(testCal1, ICSEvent.convertICSDateToCal(icsDate));
		 }
}
