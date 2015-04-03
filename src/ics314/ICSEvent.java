package ics314;

import java.util.Calendar;
import java.util.HashMap;

public class ICSEvent {
	Calendar start;
	Calendar end;
	String tmz;
	HashMap<String,String> eventProps;
	String DMSTART;
	String DMEND;
	
	public ICSEvent(Calendar start, Calendar end) {
		this.start = start;
		this.end = end;
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
		
		return year+minuteString+dayString+"T"+hourString+minuteString+secondString+"Z";
		
	}
}
