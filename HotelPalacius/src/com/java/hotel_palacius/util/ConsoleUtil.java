package com.java.hotel_palacius.util;

import java.io.IOException;

public class ConsoleUtil {
	public final static void clearConsole() {
		try {
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		} catch (InterruptedException | IOException e) {
			e.getMessage();
		}
	}
}
