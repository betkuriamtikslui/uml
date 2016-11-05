package model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import Boat.Boat;
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
	

	
	public ArrayList<String> getVerboseList(LinkedList<Member> list) {
		ArrayList<String> verboseList = new ArrayList<String>();
		String message;
		for (Member member : list) {
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
	
	

	public ArrayList<String> advancedSearch(String query){
		LinkedList<Member> list = new LinkedList<Member>();
		//name=Somename; boatNumber="5"
		String[] queryArray = query.split(";");
		String[][] arr = new String[queryArray.length][2];
		
		String[] temp;
		for(int i = 0; i< queryArray.length; i++){
			temp = queryArray[i].split("=");
			arr[i][0] = temp[0];
			arr[i][1] = temp[1];
			
			
		}
		temp = null;//clean temp
		for(Member member:memberList){
			boolean has = true;
			for(int i = 0; i< arr.length; i++){
				if(has == false){
					break;
				}
				switch(arr[i][0].toLowerCase()){
				
				case "name":
					
					if(!member.getName().contains(arr[i][1])){
						has=false;
					}
					break;
				case "numberofboats":
					if(member.getBoatList().size() != Integer.parseInt(arr[i][1])){
						has = false;
					}
					break;
					
				
				}
			}
			if(has){
				list.add(member);
			}
		}

		
		return getVerboseList(list);
	}
	
	

	public boolean deleteMember(String name) {
		for (Member member : memberList) {
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

	public ArrayList<String> search(String name) {
		LinkedList<Member> list = new LinkedList<Member>();
		for(Member member:memberList){
			boolean add = true;
			if(member.getName().equals(name)){
				list.add(member);
			}
				
		}
		
		return getVerboseList(list);
	}
}
