package ics314;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class FreeTimeEventCreator {

	public static void main(String[] args) {
		
	}
	
	public static void writeFreeTime(List<ICSEvent> events){
		int countFreeTimeEventNum = 0;
		for(ICSEvent event : events){
			String tmz = event.tmz;
			String dtstart = FreeTimeCalculator.getPropertyWithPrefix(event.eventProps, "DTSTART");
			String dtend = FreeTimeCalculator.getPropertyWithPrefix(event.eventProps, "DTEND");
			
			try {
				PrintWriter writer = new PrintWriter("freetime" + countFreeTimeEventNum +".ics");
				writer.println("BEGIN:VCALENDAR");
				writer.println("VERSION:2.0");
				writer.println("BEGIN:VEVENT");
				writer.println("DTSTART;TZID=" + tmz + ":" + dtstart);
				writer.println("DTEND;TZID=" + tmz + ":" + dtend);
				writer.println("SUMMARY:" + "Free Time");
				writer.println("END:VEVENT");
				writer.println("END:VCALENDAR");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
