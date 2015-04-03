package ics314;

import java.util.Comparator;

public class CalComparator implements Comparator<ICSEvent>{

	@Override
	public int compare(ICSEvent o1, ICSEvent o2) {
		return o1.start.compareTo(o2.start);
	}
	
	
}
