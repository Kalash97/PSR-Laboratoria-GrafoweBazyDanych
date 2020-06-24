package com.view;

import java.util.Scanner;

public class ConsoleView {
	private Scanner scanner = new Scanner(System.in);

	public void print(String msg) {
		System.out.println(msg);
	}

	public String read() {
		return scanner.nextLine();
	}

	public String read(String msg) {
		print(msg);
		return read();
	}

	public Integer getValidInt(String msg) {
		
		print(msg);
		while (true) {
			try {
				return Integer.parseInt(read());
			} catch (ClassCastException e) {
			
			}
		}
	}
	
}
