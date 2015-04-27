package ics314;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;

public class UserInputHandler {
	private Scanner scan;
	private String location;
	private String summary;
	private int priority;
	private String classification;
	private String dtstart;
	private String dtend;
	private String tmz;
	private FreeTimeCalculator ftcalc;
	private CalComparator comparator;
	private int numICSFilesCreated = 0;
	
	public static void main(String[] args) {
		UserInputHandler userInputHandler = new UserInputHandler();
		userInputHandler.mainLoop();
	}
	
	public UserInputHandler(){
		scan = new Scanner(System.in);
		ftcalc = new FreeTimeCalculator();
		comparator = new CalComparator();
	}
	
	private void mainLoop(){
		boolean programIsRunning = true;
		String userInput = "";
		Integer userOption = null;
		while(programIsRunning){
			printMainOptions();
			userInput = scan.next();
			scan.nextLine();
			userOption = parseUserInput(userInput);
			processUserInput(userOption);
		}
	}
	


	private void printMainOptions(){
		String s = "1) Create a new Event\n"
				+ "2) Find free time\n"
				+ "3) Find mutual free times\n"
				+ "4) Exit Program\n";
		System.out.println(s);
		System.out.print("Select an option: ");
	}
	
	private Integer parseUserInput(String input){
		Integer option = Integer.parseInt(input);
		return option;
	}
	
	private void createOutputFile(){
		String outputName = "calinput" + numICSFilesCreated + ".ics";
		try {
			 
			PrintWriter writer = new PrintWriter("calinput" + numICSFilesCreated++ + ".ics");
			
			writer.println("BEGIN:VCALENDAR");
			writer.println("VERSION:2.0");
			writer.println("BEGIN:VEVENT");
			writer.println("DTSTART;TZID=" + tmz + ":" + dtstart);
			writer.println("DTEND;TZID=" + tmz + ":" + dtend);
			String uid = ftcalc.generateUID();
			writer.println("UID:" + uid);
			writer.println("LOCATION:" + location);
			writer.println("SUMMARY:" + summary);
			writer.println("PRIORITY:" + priority);
			writer.println("CLASS:" + classification);
			writer.println("END:VEVENT");
			writer.println("END:VCALENDAR");
			writer.close();
	 
	    	} catch (IOException e) {
		      e.printStackTrace();
		}
		System.out.println("\nCalendar file : " + outputName + " Created");
	}
	
	private String getTime() {
		String time = scan.nextLine();
		if (time.length() != 4) {
			System.out.println("Enter a 4 digit number:");
			return getTime();
		} 
		String a = time.substring(0, 1);
		String b = time.substring(1, 2);
		String c = time.substring(2, 3);
		String d = time.substring(3, 4);
		try {
			if (Integer.parseInt(a) > 2 || Integer.parseInt(a) < 0) {
				System.out.println("Incorrect hour, Enter a 4 digit number:");
				return getTime();
			} else if (Integer.parseInt(b) > 4 || Integer.parseInt(b) < 0) {
				System.out.println("Incorrect hour, Enter a 4 digit number:");
				return getTime();
			} else if (Integer.parseInt(c) > 5 || Integer.parseInt(c) < 0) {
				System.out.println("Incorrect minute, Enter a 4 digit number:");
				return getTime();
			} else if (Integer.parseInt(d) > 9 || Integer.parseInt(d) < 0) {
				System.out.println("Incorrect minute, Enter a 4 digit number:");
				return getTime();
			} else {
				return time;
			}
		} catch (Exception e) {
			System.out.println("Enter a 4 digit number:");
			return getTime();
		}
	}
	
	private void processUserInput(Integer option){
		String startDate = "";
		String startTime = "";
		String endDate = "";
		String endTime = "";
		
		if(option.equals(1)){
			System.out.println("--Enter new event information--");
			
			Calendar start;
			Calendar end;
			boolean endIsAfterStart = false;
			
			do{
				
				do
				{
					System.out.print("Start Date(mmddyyyy): ");
					startDate = scan.nextLine();
				}while(!isDate(startDate));
					
				System.out.print("Start Time(24hr, 4 digits) ####: ");    //8 AM = 0800
	
				startTime = getTime();

				do {
					System.out.print("End Date(mmddyyyy); ");
					endDate = scan.nextLine();
				}while(!isDate(endDate));
				
				System.out.print("End Time(24hr, 4 digits) ####: ");    
				endTime = getTime();
				
				start = ICSEvent.convertICSDateToCal(startDate + startTime+"00");
				end = ICSEvent.convertICSDateToCal(endDate + endTime + "00");
				
				endIsAfterStart = end.after(start);
				if(!endIsAfterStart){
					System.out.println("End Date/Time must be after Start Date/Time");
				}
				
			}
			while(!endIsAfterStart);
			
			dtstart = (startDate.substring(4, 8) + startDate.substring(0,2) + startDate.substring(2,4) + "T" + startTime + "00Z");
			dtend = (endDate.substring(4, 8) + endDate.substring(0,2) + endDate.substring(2,4) + "T" + endTime + "00Z");

			System.out.print("Location: ");
			location = scan.nextLine();
			System.out.print("Summary: ");
			summary = scan.nextLine();
			System.out.print("Priority: ");
			priority = scan.nextInt();
			System.out.print("Classification: ");
			classification = scan.next().toUpperCase();
			
			System.out.println("[1] Pacific/Honolulu   [2] Not implemented");
			System.out.print("Select timezone: ");
			Integer timeZone = scan.nextInt();
			if (timeZone.equals(1)) {
				tmz = "Pacific/Honolulu";
			}
			createOutputFile();
			System.out.println("Press Enter to Continue\n");
			scan.nextLine();
			scan.nextLine();
		}

		else if(option.equals(2)){
			System.out.println("Enter busy time ics files separated by space\n");
			String busyTimes = scan.nextLine();
			String[] busyTimesSeparated = busyTimes.split(" ");
			ftcalc.findFreeTimes(busyTimesSeparated);
			System.out.println("Your Free Time Files have been generated named as freetimeX.ics\n\n");
		}
		
		else if(option.equals(3)) {
			//Person one files
			System.out.println("Enter person 1's free time files(separate by single space): ");
			String personOneFreeTimes = scan.nextLine();
			String[] personOneTimesSeparated = personOneFreeTimes.split(" ");
			List<ICSEvent> personOneFreeTimeList = ftcalc.convertFilenameToICSEvent(personOneTimesSeparated);
			
			//Person two files
			System.out.println("Enter person 2's free time files(separate by single space): ");
			String personTwoFreeTimes = scan.nextLine();
			String[] personTwoTimesSeparated = personTwoFreeTimes.split(" ");
			List<ICSEvent> personTwoFreeTimeList = ftcalc.convertFilenameToICSEvent(personTwoTimesSeparated);
			
			//Sort then print
			personOneFreeTimeList.sort(comparator);
			personTwoFreeTimeList.sort(comparator);
			System.out.println(personOneFreeTimeList);
			System.out.println(personTwoFreeTimeList + "\n");
			
			List<ICSEvent> p1BusyTime = FreeTimeCalculator.freeTime(personOneFreeTimeList);
			List<ICSEvent> p2BusyTime = FreeTimeCalculator.freeTime(personTwoFreeTimeList);
			
			List<ICSEvent> combinedBusyTime = combineList(p1BusyTime, p2BusyTime);
//			combinedBusyTime.addAll(p1BusyTime);
//			combinedBusyTime.addAll(p2BusyTime);
			System.out.println(combinedBusyTime);
			combinedBusyTime.sort(comparator);
			System.out.println(combinedBusyTime);
			List<ICSEvent> combinedFreeTime = FreeTimeCalculator.freeTime(combinedBusyTime);
			System.out.println(combinedFreeTime);
			ftcalc.writeFreeTime(combinedFreeTime, "MutualFreeTimes");
			
		}
		else{
			System.out.println("Exiting Program");
			System.exit(0);
		}
	}
	
	private List<ICSEvent> combineList(List<ICSEvent> l1, List<ICSEvent> l2){
		List<ICSEvent> combined = new ArrayList<ICSEvent>();
		for(ICSEvent e : l1){
			combined.add(e);
		}
		
		for(ICSEvent e : l2){
			combined.add(e);
		}
		return combined;
	}
	
	//mmddyyyy
	private boolean isDate(String date) { 
		try {
			GregorianCalendar cal = new GregorianCalendar();
			cal.setLenient(false); 
			cal.set(GregorianCalendar.YEAR, Integer.valueOf(date.substring(4,8)));
			cal.set(GregorianCalendar.MONTH, Integer.valueOf(date.substring(0,2)) - 1); //Months are 0-11
			cal.set(GregorianCalendar.DATE, Integer.valueOf(date.substring(2,4)));
			
			cal.getTime(); //throws exception if any field invalid
			
			if (Integer.valueOf(date) != null) { //no letters entered
				return true;
		}
		}
		catch (Exception e){
			System.out.println("Enter a correct date format!\n");
		}
		return false;
	}
}
