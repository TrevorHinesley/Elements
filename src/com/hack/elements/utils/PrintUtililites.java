package com.hack.elements.utils;

import com.hack.elements.ElementsGame;

public class PrintUtililites {

	public static void logPrint(String s) {
		if(ElementsGame.LOGGING_ON)
			System.out.print(s);
	}
	
	public static void logPrintln(String s) {
		if(ElementsGame.LOGGING_ON)
			System.out.println(s);
	}
	
	public static void logPrintln() {
		if(ElementsGame.LOGGING_ON)
			System.out.println();
	}
	
}
