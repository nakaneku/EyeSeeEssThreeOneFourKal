package ics314;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class TesterClass {

	public static void main(String[] args) {
		Calendar calendar = new GregorianCalendar(2015, 3, 2, 3, 12 ,12);
		Calendar calendar2 = new GregorianCalendar(2015, 3, 2, 5, 12 ,12);
		Calendar calendar3 = new GregorianCalendar(2015, 3, 2, 6, 12 ,12);
		Calendar calendar4 = new GregorianCalendar(2015, 3, 2, 8, 12 ,12);
		ICSEvent f = new ICSEvent(calendar, calendar2);
		ICSEvent g = new ICSEvent(calendar3, calendar4);
		List<ICSEvent> events = new ArrayList<ICSEvent>();
		events.add(g);
		events.add(f);
		System.out.println(events);
	}

}
