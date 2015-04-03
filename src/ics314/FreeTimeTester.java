package ics314;

import static org.junit.Assert.*;

import org.junit.Test;

public class FreeTimeTester {

	@Test
	public void test() {
		FreeTimeCalculator ftc = new FreeTimeCalculator();
		int a = 3;
		int b = 7;
		int c = ftc.add(a, b);
		assertEquals(9, c);
	}

}
