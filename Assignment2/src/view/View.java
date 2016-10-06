package view;

import java.util.ArrayList;
import java.util.Scanner;

public class View {
	Scanner scanner;
	public View(){
		scanner = new Scanner(System.in);
	}
	public void print(String s){
		System.out.println(s);
	}
	public void print(ArrayList<String> list){
		for(String element: list){
			System.out.println(element);
		}
	}
	public String getCommand(String message){
		System.out.println(message);
		return scanner.nextLine();	

	}
	private void clearScreen(){		//clears screen 
		for(int i = 0; i<20; i++){
			System.out.println();
		}
	}
	
}
