package ics314;

import java.util.Calendar;

public class ICSEvent {
	Calendar start;
	Calendar end;
	
	public ICSEvent(Calendar start, Calendar end) {
		this.start = start;
		this.end = end;
	}
	
	public String toString() {
		return start.getTime() + " - " + end.getTime();
	}
}
