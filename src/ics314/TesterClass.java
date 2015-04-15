package ics314;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class TesterClass {

	public static void main(String[] args) {
		CalComparator comparator = new CalComparator();
		Calendar calendarStart1 = new GregorianCalendar(2015, 3, 1, 3, 0, 0); //Apr 1
		Calendar calendarEnd1 =   new GregorianCalendar(2015, 3, 1, 3, 0, 0);
		Calendar calendarStart2 = new GregorianCalendar(2015, 3, 1, 1, 0, 0); //Apr 2
		Calendar calendarEnd2 =   new GregorianCalendar(2015, 3, 1, 2, 0, 0);
		Calendar calendarStart3 = new GregorianCalendar(2015, 3, 1, 4, 0, 0); //Apr 3
		Calendar calendarEnd3 =   new GregorianCalendar(2015, 3, 1, 6, 0, 0);
		Calendar calendarStart4 = new GregorianCalendar(2015, 3, 1, 17, 0, 0); //Apr 4
		Calendar calendarEnd4 =   new GregorianCalendar(2015, 3, 1, 18, 0, 0);
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
		events = freeTime(events);
		System.out.println(events);
		
	    //System.out.println(calendarStart1.equals(calendarEnd1));
	}
	
	public static ICSEvent testConvert() {
		ICSEvent constructorTest = new ICSEvent("20150508T183000Z", "20150111T190000Z");
		List<ICSEvent> events2 = new ArrayList<ICSEvent>();
		events2.add(constructorTest);
		System.out.println(events2);
		
		return constructorTest;
	}
	
	public static List<ICSEvent> freeTime(List<ICSEvent> list) {
		List<ICSEvent> rlist = new ArrayList<ICSEvent>();
		int year = list.get(0).start.get(Calendar.YEAR);
		int month = list.get(0).start.get(Calendar.MONTH);
		int day = list.get(0).start.get(Calendar.DATE);
		int hour = list.get(0).start.get(Calendar.HOUR_OF_DAY);
		int minute = list.get(0).start.get(Calendar.MINUTE);
		int second = list.get(0).start.get(Calendar.SECOND);
		
	    if(hour > 0) {
	    	rlist.add(new ICSEvent(new GregorianCalendar(year, month, day, 0, 0 ,0), 
	    			  new GregorianCalendar(year, month, day, hour, minute, second)));
	    }

		while (list.size() > 1) {
			if (list.get(0).end.get(Calendar.HOUR_OF_DAY) >= list.get(1).start.get(Calendar.HOUR_OF_DAY)) {
				list.add(0, new ICSEvent(new GregorianCalendar(year, month, day, list.get(0).start.get(Calendar.HOUR_OF_DAY), 
						list.get(0).start.get(Calendar.MINUTE), list.get(0).start.get(Calendar.SECOND)), 
						new GregorianCalendar(year, month, day, list.get(1).end.get(Calendar.HOUR_OF_DAY), 
						list.get(1).end.get(Calendar.MINUTE), list.get(1).end.get(Calendar.SECOND))));
				list.remove(2);
				list.remove(1);
			} else {
				rlist.add(new ICSEvent(new GregorianCalendar(year, month, day, list.get(0).end.get(Calendar.HOUR_OF_DAY), 
						list.get(0).end.get(Calendar.MINUTE), list.get(0).end.get(Calendar.SECOND)), 
						new GregorianCalendar(year, month, day, list.get(1).start.get(Calendar.HOUR_OF_DAY), 
						list.get(1).start.get(Calendar.MINUTE), list.get(1).start.get(Calendar.SECOND))));
				list.remove(0);
			}
		}
		if(list.get(0).end.get(Calendar.HOUR_OF_DAY) < 24) {
			rlist.add(new ICSEvent(new GregorianCalendar(year, month, day, list.get(0).end.get(Calendar.HOUR_OF_DAY), 
					list.get(0).end.get(Calendar.MINUTE), list.get(0).end.get(Calendar.SECOND)), 
					new GregorianCalendar(year, month, day, 24, 0, 0)));
		}
		return rlist;
	}

}
