package model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Boat.Boat;
import Evaluate.Evaluate;
import Exceptions.WrongQueryException;
import People.Member;

public class Model {
	private ArrayList<Member> memberList;

	public Model() {
		 memberList = new ArrayList<Member>();
	}

	public Model(ArrayList<Member> memberList) {
		this.memberList = memberList;
	}
	public ArrayList<Member> getMemberList(){
		return memberList;
	}
	public Member authentificate(String name) {
		for (Member member : memberList) {
			if (member.getName().equals(name)) {
				return member;
			}
		}
		return null;
	}

	public boolean editMemberInfo(String oldName, String oldID, String newName, String newId){
		for(Member member: memberList){
			if(member.getName().equals(oldName)&& member.getPersonalID().equals(oldID)){
				member.setName(newName);
				member.setPersonalID(newId);
				return true;
			}
		}
		return false;
	}
	
	public ArrayList<String> getCompactList() {
		
		ArrayList<String> compactList = new ArrayList<String>();
		String message;
		for (Member member : memberList) {
			message = member.getName() + " " + member.getUniqueID() + " " + member.getBoatList().size();
			compactList.add(message);
		}
		return compactList;
	}

	public ArrayList<String> getVerboseList() {
		ArrayList<String> verboseList = new ArrayList<String>();
		String message;
		for (Member member : memberList) {
			message = member.getName() + " " + member.getUniqueID() + " " + member.getPersonalID();
			verboseList.add(message);
			message = "Ships: ";
			for (Boat boat : member.getBoatList()) {
				message += boat.getType().toString() + " " + boat.getLength();
				verboseList.add(message);
				message = "";
			}
		}
		return verboseList;
	}
	
//	protected String[][] getCommands(String[] argument){
//		String[] results = new String[argument.length];
//
//		
//		
//		return results;
//	}
	
	
//	public ArrayList<Member> Query(String query) {
//		ArrayList<Member> list = new ArrayList<Member>(); // copy
//															// memberList
//		// should look at name
//		// uniqueID
//		// id
//		// age
//		// day,month,year
//		// boat Number
//		Evaluate eval = new Evaluate();
//		Matcher m;
//		
//		
//		
//		
//		String[] results = new String[temp.length];
//		String queryCopy = "";
//		queryCopy = String.valueOf(query);
//		queryCopy = queryCopy.replaceAll("^\\s|\n\\s|\\s$", "");
//		queryCopy = queryCopy.replaceAll("[^0-9a-zA-Z:-]+", ",");
//		String[] temp = queryCopy.split(","); // splits by commas so the result
//												// of spliting
//												// name:someName,age:17,
//												// would be name:someName in 0
//												// place
//												// annd age1 in 1;
//
//		String[][] commands = new String[temp.length][2];
//		for (int i = 0; i < temp.length; i++) {
//			if (temp[i] != "") {
//				commands[i][0] = temp[i].split(":")[0];
//				commands[i][1] = temp[i].split(":")[1];
//			}
//		}
//
//		for (Member member : memberList) { // loop through all members
//			for (int i = 0; i < commands.length; i++) { // loop through all
//														// commands which were
//														// passed
//				String pattern = commands[i][1];
//				Pattern p = Pattern.compile(pattern); // creates
//														// pattern
//				switch (commands[i][0]) { // switch between all
//											// commands
//				case "name":
//					m = p.matcher(member.getName()); // matches to name of
//														// member
//					if (m.find()) {
//						results[i] = "true";
//					}
//					break;
//				// case "ID":
//				// m = p.matcher(member.getUniqueID());
//				// if (m.find()) {
//				// results[i] = "true";
//				// }
//				// break;
//				case "personalID":
//					m = p.matcher(member.getDay());
//					if (m.find()) {
//						results[i] = "true";
//					}
//					break;
//
//				case "birthday":
//					if (member.getDay().equals(commands[i][1])) {
//						results[i] = "true";
//
//					}
//					break;
//
//				case "birthMonth":
//					if (member.getMonth().equals(commands[i][1])) {
//						results[i] = "true";
//
//					}
//					break;
//				case "birthYear":
//					if (member.getYear().equals(commands[i][1])) {
//						results[i] = "true";
//					}
//					break;
//				case "numberOFBoats:":
//					m = p.matcher(Integer.toString(member.getBoatList().size())); // matches
//																					// to
//																					// name
//																					// of
//					if (m.find()) {
//						results[i] = "true";
//					}
//					break;
//
//				default:
//
//				}
//			}
//			System.out.println(Arrays.toString(results));
//			if (eval.eval(replace(query, temp, results))) {
//				list.add(member);
//			}
//		}
//
//		return list;
//
//	}

	private String replace(String original, String[] toBeReplaced, String[] newValues) {
		for (int i = 0; i < toBeReplaced.length; i++) {

			original.replace(toBeReplaced[i], newValues[i]);
			
		}
		System.out.println("replace + " + original);

		return original;
	}

	

	public boolean deleteMember(String name) {
		for (Member member : memberList) {
			System.out.println(member.getName());
			if (member.getName().equals(name) ) {
				memberList.remove(member);
				return true;

			}
		}
		return false;
	}

	public String getMemberInfo(Member member) {
		return member.getName() + " " + member.getPersonalID() + " " + member.getUniqueID() + " "
				+ member.getBoatList().size();

	}

	public boolean addMember(String name, String id) {
		if (nameIsValid(name) && dateIsValid(id.substring(0, 9))) {
			memberList.add(new Member(name, id));
			return true;
		} else {
			return false;
		}
	}

	public boolean dateIsValid(String date) {
		String DATE_FORMAT = "yyyy-mm-dd";
		String formatedDate = "";
		formatedDate += date.substring(0, 4) + "-" + date.substring(4, 6) + "-" + date.substring(6, 8);
		try {
			DateFormat df = new SimpleDateFormat(DATE_FORMAT);
			df.setLenient(false);
			df.parse(formatedDate);
			return true;
		} catch (ParseException e) {
			return false;
		}
	}

	public boolean nameIsValid(String name) {
		if (!(name.length() == 0)) {

			for (int i = 0; i < name.length(); i++) {

				if (!Character.isAlphabetic(name.charAt(i))) {
					return false;
				}
			}
			return true;

		}
		return false;
	}

	public int getLength() {
		return memberList.size();
	}

	public void addMember(Member member) {
		memberList.add(member);
	}
}
