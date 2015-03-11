package ics314;

import java.util.Scanner;

public class UserInputHandler {
	private Scanner scan;

	public static void main(String[] args) {
		UserInputHandler userInputHandler = new UserInputHandler();
		userInputHandler.mainLoop();
		System.out.println("Hello world");
		

	}
	
	public UserInputHandler(){
		scan = new Scanner(System.in);
	}
	
	private void mainLoop(){
		boolean programIsRunning = true;
		String userInput = "";
		Integer userOption = null;
		while(programIsRunning){
			printMainOptions();
			userInput = scan.next();
			userOption = parseUserInput(userInput);
			processUserInput(userOption);
		}
	}
	


	private void printMainOptions(){
		String s = "1) Create a new Event\n"
				+ "2) Print OutputFile\n"
				+ "3) Exit Program\n";
		System.out.println(s);
	}
	
	private Integer parseUserInput(String input){
		Integer option = Integer.parseInt(input);
		return option;
	}
	
	private void processUserInput(Integer option){
		if(option.equals(1)){
			System.out.println("Create New Event Menu");
		}
		else if(option.equals(2)){
			System.out.println("Export Output File Menu");
		}
		else{
			System.out.println("Exiting Program");
			System.exit(0);
		}
	}
}
