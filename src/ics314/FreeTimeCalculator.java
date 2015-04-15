package ics314;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class FreeTimeCalculator {
	private String timezone;

	public static void main(String[] args) {
		
		if(args.length <= 0){
			System.err.println("Proper Usage: $java FreeTimeCalculator <filename1> <filename2> ...");
		}
		//Check for invalid filenames
		
		FreeTimeCalculator ftc = new FreeTimeCalculator();
		ftc.run(args); //Just to get out of static context
		
	}
	
	public int add(int a, int b) {
		return a + b;
	}
	
	public void run(String[] args){		
		//Call mark algorithm pass icsEvents
		//get back list of ics free time Events and pass to writefree time.
		List<ICSEvent> icsEvents = convertFilenameToICSEvent(args);
		icsEvents = TesterClass.freeTime(icsEvents);
		writeFreeTime(icsEvents);
		
	}
	
	public List<ICSEvent> convertFilenameToICSEvent(String[] filenames) {

		List<HashMap<String, String>> events = new ArrayList<HashMap<String,String>>();
		timezone = getTimeZone(parse(filenames[0])); //Gets the properties from the first event and gets the timezone
		
		for(String filename: filenames){
			HashMap<String, String> e = parse(filename);
			if(timezone.equals(getTimeZone(e))){
				events.add(e);
			}
			else{
				System.err.println("Not all ICS Files have the same timezone");
				System.err.println(getTimeZone(e) + "does not match = " + timezone);
				System.exit(1);
			}
		}

		List<ICSEvent> icsEvents = new ArrayList<ICSEvent>();
		for(HashMap<String,String> event : events){
			String startTime = getPropertyWithPrefix(event, "DTSTART");
			String endTime = getPropertyWithPrefix(event, "DTEND");
			
			Calendar c1 = ICSEvent.convertICSDateToCal(startTime);
			Calendar c2 = ICSEvent.convertICSDateToCal(endTime);
			ICSEvent ie = new ICSEvent(c1, c2);
			ie.tmz = timezone;
			ie.eventProps = event;
			icsEvents.add(ie);
			
			//System.out.println(ie);
			//System.out.println();	
		}
		return icsEvents;
	}
	
	public String eventPropertyToString(HashMap<String,String> eventPropMap){
		Set<String> keys = eventPropMap.keySet();
		String stringRep = "";
		for(String key : keys){
			stringRep += key + " : "  +eventPropMap.get(key) +"\n";
		}
		return stringRep;
	}
	
	//TODO Really Hacky
	/**
	 * Returns the Timezone if the DTSTART and DTEND timezones agree
	 * Otherwise returns null
	 * @param event the event property file.
	 * @return
	 */
	public String getTimeZone(HashMap<String, String> event){
		Set<String> propKeys = event.keySet();
		String startTZ = null;
		String endTZ = null;
		for(String prop: propKeys){
			if(prop.startsWith("DTSTART")){
				String[] parseTimezone = prop.split("TZID="); 
				startTZ = parseTimezone[1];
			}
			else if(prop.startsWith("DTEND")){
				String[] parseTimezone = prop.split("TZID="); 
				endTZ = parseTimezone[1];
			}
		}
		
		if(startTZ.equals(endTZ)){
			return startTZ;
		}
		else{
			return null;
		}
	}
	
	public static String getPropertyWithPrefix(HashMap<String,String> event, String propertyPrefix){
		Set<String> keys = event.keySet();
		for(String key : keys){
			if(key.startsWith(propertyPrefix)){
				return event.get(key);
			}
		}
		return null; //handle error here.
	}
	
	public String generateUID(){
		String UID = "";
		Calendar cal = Calendar.getInstance();
		UID = "GRAPES";
		UID += cal.getTimeInMillis();
		UID += "@TeamGrape";
		return UID;
		
	}
	public void writeFreeTime(List<ICSEvent> events){
		int countFreeTimeEventNum = 0;
		for(ICSEvent event : events){
			String tmz = timezone;
			String dtstart = ICSEvent.calToStr(event.start);
			String dtend = ICSEvent.calToStr(event.end);
			String UID = generateUID();
			
			try(PrintWriter writer = new PrintWriter("freetime" + countFreeTimeEventNum +".ics")) {
				writer.println("BEGIN:VCALENDAR");
				writer.println("VERSION:2.0");
				writer.println("BEGIN:VEVENT");
				writer.println("DTSTART;TZID=" + tmz + ":" + dtstart);
				writer.println("UID:" + UID);
				writer.println("DTEND;TZID=" + tmz + ":" + dtend);
				writer.println("SUMMARY:" + "Free Time");
				writer.println("END:VEVENT");
				writer.println("END:VCALENDAR");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			countFreeTimeEventNum++;
		}
	}
	
	public HashMap<String, String> parse(String filename){
		HashMap<String, String> icsFileProperties = new HashMap<String, String>();
		
		File icsFile = new File(filename);

		try {
			Scanner scan = new Scanner(icsFile);
			String propertyLine;
			
			while(scan.hasNextLine()){
				propertyLine = scan.nextLine();
				String[] properties = propertyLine.split(":");
				icsFileProperties.put(properties[0], properties[1]);
			}
		} catch (FileNotFoundException e) {
			System.err.println("Invalid filename: " + filename);
			e.printStackTrace();
		}
		return icsFileProperties;
		
	}
	
	


}
