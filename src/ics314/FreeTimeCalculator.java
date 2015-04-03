package ics314;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class FreeTimeCalculator {

	public static void main(String[] args) {
		
		if(args.length <= 0){
			System.err.println("Proper Usage: $java FreeTimeCalculator <filename1> <filename2> ...");
		}
		//Check for invalid filenames
		
		FreeTimeCalculator ftc = new FreeTimeCalculator();
		ftc.run(args); //Just to get out of static context
	}
	
	public void run(String[] args){
		String[] filenames = args;
		

		List<HashMap<String, String>> events = new ArrayList<HashMap<String,String>>();
		
		for(String filename: filenames){
			events.add(parse(filename));
		}
		
		
		for(HashMap<String,String> event : events){
			Set<String> propKeys = event.keySet();
			for(String prop: propKeys){
				System.out.println(prop + " : " + event.get(prop));
			}
		}
		
		System.out.println(Arrays.toString(args));
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
