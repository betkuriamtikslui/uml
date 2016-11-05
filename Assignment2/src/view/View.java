package view;

import java.util.ArrayList;
import java.util.Scanner;

import Boat.Boat;
import People.Member;

public class View {
	private String[] commands = new String[]{"login","Verboselist", "CompactList", "Logout", "Addbouat", "EditBoat", "DeleteBoat", "ListBoats", "AddMember", "DeleteMember", "Search"};
	private Scanner scanner;

	public View() {
		scanner = new Scanner(System.in);
	}

	public void print(String s) {
		System.out.println(s);
	}

	public void print(ArrayList<String> list) {
		for (String element : list) {
			System.out.println(element);
		}
	}

	public String[] getCommand(){
		return scanner.nextLine().split(" ");
	}

	public void clearScreen() { // clears screen
		for (int i = 0; i < 20; i++) {
			System.out.println();
		}
	}

	public String verboseList(ArrayList<Member> memberList) {
		for (Member member : memberList) {
			System.out.println(
					member.getName() + " " + member.getUniqueID() + " " + member.getPersonalID() + "\n Ships: \n");
			for (Boat boat : member.getBoatList()) {
				System.out.println(boat.getType().toString() + " " + boat.getLength());
			}
		}

		return scanner.nextLine();

	}

	public String compactList(ArrayList<Member> memberList) {

		for (Member member : memberList) {
			System.out
					.println(member.getName() + " " + member.getUniqueID() + " " + member.getBoatList().size() + "\n");
		}
		return scanner.nextLine();

	}

	public void printLoggedInAlready() {
		System.out.println("You are already logged in");
	}

	public void printListOfCommands() {
		System.out.println("---------------------------------------------");
		System.out.println("List of commands");
		System.out.println("---------------------------------------------");
		for(String command: commands){
			System.out.println(command);
		}
	}

	public void invalidParameters() {
		System.out.println("invalid parameters");
	}

	public void notLoggedin() {
		System.out.println("you are not logged in");
	}

	public void memberAddedSuccessfully() {
		System.out.println();
	}

	public void boatAdded() {
		System.out.println("Boat has been added successfully");
	}

	public void boatWasNotAdded() {
		System.out.println("We were not able to add your boat");
	}

	public void informationWasChanged() {
		System.out.println("Information was change succesfully");
	}

	public void informationWasNotChanged() {
		System.out.println(
				"we were not able to change information, either user does not exist or the information given is wrong");
	}

	public void dontHavePermission() {
		System.out.println("You do not have permission to access this command");
	}

	public void boatList(ArrayList<Boat> boatList) {
		for (Boat boat : boatList) {
			System.out.println(boat.getType() + " " + boat.getLength());
		}
	}

	public void operationWasSuccessfull() {
		System.out.println("Your request was fulfilled");
	}

	public void savingFiles() {
		System.out.println("Saving files");
	}

	public void loggedInAsSecretary() {
		System.out.println("You are logged in as secretary");;
	}
	

}
