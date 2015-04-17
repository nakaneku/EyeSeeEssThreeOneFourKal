package ics314;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;

public class ICSEvent {
	public Calendar start;
	public Calendar end;
	public String tmz;
	public HashMap<String,String> eventProps;
	public String DMSTART;
	public String DMEND;
	
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
	
	public static void main(String [] args){
		Calendar now = Calendar.getInstance();
		System.out.println(calToStr(now));
	}
	
	public static String calToStr(Calendar cal){
		String year = cal.get(Calendar.YEAR) + "";
		int month = (cal.get(Calendar.MONTH)+1);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		int minute = cal.get(Calendar.MINUTE);
		int second = cal.get(Calendar.SECOND);
		
		
		String monthString = "";
		String dayString = "";
		String hourString = "";
		String minuteString = "";
		String secondString = "";
		
		if(month < 10){
			monthString = "0"+month;
		}else{
			monthString = "" + month;
		}
		
		if(day < 10){
			dayString = "0" + day;
		}else{
			dayString = "" + day;
		}
		
		if(hour < 10){
			hourString = "0"+hour;
		}else{
			hourString = "" + hour;
		}
		
		if(minute<10){
			minuteString = "0" + minute;
			
		}else{
			minuteString = "" + minute;
		}
		
		if(second<10){
			secondString = "0" + second;
		}else{
			secondString = "" + second;
		}
		
		return year+monthString+dayString+"T"+hourString+minuteString+secondString+"Z";
		
	}
}
