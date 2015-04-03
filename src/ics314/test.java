package ics314;

import java.util.Calendar;

public class test {

	public static void main(String[] args) {
		String UID = "";
		Calendar cal = Calendar.getInstance();
		UID = "GRAPES";
		UID += cal.getTimeInMillis();
		UID += "@TeamGrape";
		System.out.println(UID);

	}

}
