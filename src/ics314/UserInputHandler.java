package ics314;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
				+ "2) Print OutputFile\n"
				+ "3) Test print\n"
				+ "4) Find free time, not implemented\n"
				+ "5) Find mutual free times\n"
				+ "6) Exit Program\n";
		System.out.println(s);
		System.out.print("Select an option: ");
	}
	
	private Integer parseUserInput(String input){
		Integer option = Integer.parseInt(input);
		return option;
	}
	
	private void processUserInput(Integer option){
		String startDate = "";
		String startTime = "";
		String endDate = "";
		String endTime = "";
		
		if(option.equals(1)){
			System.out.println("--Enter new event information--");
			scan.nextLine(); //extra newline somewhere that was messing up input
			
			System.out.print("Start Date(mmddyyyy): ");
			startDate = scan.nextLine();
			System.out.print("Start Time(24hr, 4 digits) ####: ");    //8 AM = 0800
			startTime = scan.nextLine();
			System.out.print("End Date(mmddyyyy): ");
			endDate = scan.nextLine();
			System.out.print("End Time(24hr, 4 digits) ####: ");    
			endTime = scan.nextLine();
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
		}
		
		else if(option.equals(2)){
			try {
				 
				PrintWriter writer = new PrintWriter("calinput.ics");
				
				writer.println("BEGIN:VCALENDAR");
				writer.println("VERSION:2.0");
				writer.println("BEGIN:VEVENT");
				writer.println("DTSTART;TZID=" + tmz + ":" + dtstart);
				writer.println("DTEND;TZID=" + tmz + ":" + dtend);
				writer.println("UID:6r3rq0kvkq1ue4jlv5f71c1dr8@google.com");
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
		}
		
		else if(option.equals(3)){
			System.out.println("DTSTART;TZID=" + tmz + ":" + dtstart);
			System.out.println("DTEND;TZID=" + tmz + ":" + dtend);
			System.out.println("LOCATION:" + location);
			System.out.println("SUMMARY:" + summary);
			System.out.println("PRIORITY:" + priority);
			System.out.println("CLASS:" + classification);
		}
		
		else if(option.equals(5)) {
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
			
			List<ICSEvent> p1BusyTime = TesterClass.freeTime(personOneFreeTimeList);
			List<ICSEvent> p2BusyTime = TesterClass.freeTime(personTwoFreeTimeList);
			
			List<ICSEvent> combinedBusyTime = combineList(p1BusyTime, p2BusyTime);
//			combinedBusyTime.addAll(p1BusyTime);
//			combinedBusyTime.addAll(p2BusyTime);
			System.out.println(combinedBusyTime);
			combinedBusyTime.sort(comparator);
			System.out.println(combinedBusyTime);
			List<ICSEvent> combinedFreeTime = TesterClass.freeTime(combinedBusyTime);
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
}
