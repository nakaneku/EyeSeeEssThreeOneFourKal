package ics314;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class TesterClass {

	public static void main(String[] args) {
		CalComparator comparator = new CalComparator();
		Calendar calendarStart1 = new GregorianCalendar(2015, 3, 1, 3, 12 ,12); //Apr 1
		Calendar calendarEnd1 = new GregorianCalendar(2015, 3, 1, 5, 12 ,12);
		Calendar calendarStart2 = new GregorianCalendar(2015, 3, 2, 6, 12 ,12); //Apr 2
		Calendar calendarEnd2 = new GregorianCalendar(2015, 3, 2, 8, 12 ,12);
		Calendar calendarStart3 = new GregorianCalendar(2015, 3, 3, 3, 12 ,12); //Apr 3
		Calendar calendarEnd3 = new GregorianCalendar(2015, 3, 3, 5, 12 ,12);
		Calendar calendarStart4 = new GregorianCalendar(2015, 3, 4, 6, 12 ,12); //Apr 4
		Calendar calendarEnd4 = new GregorianCalendar(2015, 3, 4, 8, 12 ,12);
		ICSEvent f = new ICSEvent(calendarStart1, calendarEnd1); //Apr 1
		ICSEvent g = new ICSEvent(calendarStart2, calendarEnd2); //Apr 2
		ICSEvent h = new ICSEvent(calendarStart3, calendarEnd3); //Apr 3
		ICSEvent i = new ICSEvent(calendarStart4, calendarEnd4); //Apr 4
		List<ICSEvent> events = new ArrayList<ICSEvent>();
		events.add(g);  //Apr 2
		events.add(f);  //Apr 1
		events.add(i); // Apr 4
		events.add(h); // Apr 3
		events.sort(comparator);
		//System.out.println(events);
		
		System.out.println();
		testConvert();
	}
	
	public static ICSEvent testConvert() {
		ICSEvent constructorTest = new ICSEvent("20150508T183000Z", "20150111T190000Z");
		List<ICSEvent> events2 = new ArrayList<ICSEvent>();
		events2.add(constructorTest);
		System.out.println(events2);
		
		return constructorTest;
	}

}
