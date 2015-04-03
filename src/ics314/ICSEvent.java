package ics314;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class ICSEvent {
	Calendar start;
	Calendar end;
	
	public ICSEvent(Calendar start, Calendar end) {
		this.start = start;
		this.end = end;
	}
	
	public ICSEvent(String start, String end) {
		Calendar startTime = convertICSDateToCal(start);
		Calendar endTime = convertICSDateToCal(end);
		this.start = startTime;
		this.end = endTime;
	}
	
	public static Calendar convertICSDateToCal(String icsDateInput) {
		int year = Integer.valueOf(icsDateInput.substring(0,4)); 
		int month = Integer.valueOf(icsDateInput.substring(4,6)); 
		int day = Integer.valueOf(icsDateInput.substring(6,8)); 
		int hour = Integer.valueOf(icsDateInput.substring(9,11));
		int minute = Integer.valueOf(icsDateInput.substring(11,13));
		int second = Integer.valueOf(icsDateInput.substring(13,15));
		
		return new GregorianCalendar(year, month - 1, day, hour, minute, second);
	}
	
	public String toString() {
		return start.getTime() + " - " + end.getTime();
	}
}
