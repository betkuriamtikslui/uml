package controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Boat.Boat;
import Boat.BoatEnum;
import People.Member;
import People.Secretary;
import Storage.Storage;
import model.Model;
import view.View;

public class MemberController {

	// objects
	private Model model;
	private View view;
	private Member session;
	private Secretary admin;
	private static boolean go;
	private Storage store;
	private String[][] notLoggedCommandList = { { "login", "login {name}" }, { "verboseList", "verboseList" },
			{ "compactList", "compactList" } ,{"quit", "{quit} --quit and save "}};
	private String[][] loggedCommandList = { { "verboseList", "verboseList" }, { "compactList", "compactList" },
			{ "logout", "logout" }, { "addBoat", "addboat length type" }, { "logout", "logout" },
			{ "editBoat", "editBoat, {boatlength} {boattype} {newLength}  {newType}" },
			{ "deleteboat", "deleteboat length type" }, {"listBoats", "listBoats"}};
	private String[][] secretaryList = { { "verboseList", "verboseList" }, { "compactList", "compactList" },
			{ "createMember", "createMember name personalid" }, { "deleteMember", "deleteMember {name} {personalID}" },
			{ "logout", "logout" },{"search", "search {name}}"}};

	public MemberController() {
		store = new Storage();
		ArrayList<Member> list = store.getMemberList();
		if(list == null){
			model = new Model();
		}else{
			model = new Model(list);
		}
		view = new View();
		go = true;
	}

	// member Methods
	public void addMember(String name, String id) {
		// if(model.addMember(name, id)){
		// view.print(created);
		// }else{
		// view.print(wrongDate);
		// }
	}

	public void authentificate(String name) {
		session = model.authentificate(name);
	}

	public void changeMemberName(String newName) {
		session.setName(newName);
	}

	// boat methods
	public void addBoat(double length, BoatEnum type) {
		session.addBoat(new Boat(length, type));
	}

	public void changeBoatLength(double oldLength, BoatEnum type, double newLength) {
		for (Boat boat : session.getBoatList()) {
			if (boat.getLength() == oldLength && boat.getType() == type) {
				boat.setLength(newLength);
			}
		}
	}

	public void changeBoatType(double length, BoatEnum type, BoatEnum newType) {
		for (Boat boat : session.getBoatList()) {
			if (boat.getLength() == length && boat.getType() == type) {
				boat.setType(newType);
			}
		}
	}

	public void run() {
		String[] values;
		
		while (go) {
			view.print("---------------------------------------------");
			view.print("List of commands");
			view.print("---------------------------------------------");
			if (session != null) {
				view.print(session.getName());
				for (int i = 0; i < loggedCommandList.length; i++) {
					view.print(loggedCommandList[i][1]);
				}

			} else if (admin != null) {
				view.print("You are logged in as secretary");
				for (int i = 0; i < secretaryList.length; i++) {
					view.print(secretaryList[i][1]);
				}

			} else {
				for (int i = 0; i < notLoggedCommandList.length; i++) {
					view.print(notLoggedCommandList[i][1]);
				}
			}
			values = view.getCommand("Enter Command").split(" ");
			view.print("---------------------------------------------");

			switch (values[0]) {
			case "login":
				if (admin == null && session == null) {
					try {
						if (values[1].equals("secretary")) {
							admin = new Secretary("", "");
						} else {
							authentificate(values[1]);
						}
					} catch (Exception e) {
						view.print("invalid parameter");
					}
				} else {
					view.print("You are already logged in");
				}
				break;
			case "logout":
				if (admin != null || session != null) {
					admin = null;
					session = null;
				} else {
					view.print("you are not logged in");
				}

				break;
			case "verboseList":
				view.print(model.getVerboseList());
				
				break;
			case "compactList":
				view.print(model.getCompactList());
				break;

			case "createMember":
				if (values.length == 3 && admin != null) {
					try {
						model.addMember(values[1], values[2]);
						view.print("Member was added successfully!");	
						store.saveLst(model.getMemberList());

					} catch (Exception e) {
						view.print("unable to add member");
					}
				} else {
					view.print("Wrong parameters");
				}
				break;
				
			case "deleteMember":
				if( admin != null){
					model.deleteMember(values[1]);
					store.saveLst(model.getMemberList());

				}
				break;
			case "addBoat":
				try{
				session.addBoat(new Boat(Double.parseDouble(values[1]),BoatEnum.valueOf(values[2])));
				
				view.print("added");
				store.saveLst(model.getMemberList());

				}catch(Exception e){
					view.print("We were not able to add your boat");
				}
				break;
			case "editMember":
				if(admin != null){
					if(values.length != 5){
						if(model.editMemberInfo(values[1], values[2], values[3], values[4])){
							view.print("Information was change succesfully");
							store.saveLst(model.getMemberList());

						}else{
							view.print("we were not able to change information, either user does not exist or the information given is wrong");
						}
					}else{
						view.print("Wrong arguments");
					}
				}else{
					view.print("You do not have permissions to change info");
				}
				break;
				
			case "listBoats":
				view.print(session.getStringBoatList());
				break;
				
			case "deleteBoat":
				if(session != null && values.length == 3){
					if(session.deleteBoat(BoatEnum.valueOf(values[2]), Double.parseDouble(values[1]))){
						store.saveLst(model.getMemberList());
						view.print("Boat was deleted");
					}else{
						view.print("we were not able to delete boat");
					}
				}
				break;
				
			case "editBoat":
				if(session != null && values.length == 5){
					if(session.editBoat(Double.parseDouble(values[1]), BoatEnum.valueOf(values[2]), Double.parseDouble(values[3]), BoatEnum.valueOf(values[4]))){
						store.saveLst(model.getMemberList());
						view.print("Boat was deleted");
					}else{
						view.print("we were not able to delete boat");
					}
				}
				break;
			case "quit":
				go = false;
				view.print("Saving files");
				store.saveLst(model.getMemberList());
				break;
			case "search":
				ArrayList<String> list;
				if(admin != null && values.length ==2){
					list = model.search(values[1]);
					view.print(list);
				}
				
				break;
			case "queryExample":
				view.print("********************");
				
				view.print("queryExample");
				
				view.print("number of obats is 3");
				view.print( model.advancedSearch("numberofboats=3"));
				view.print("********************");
				view.print("name contains ni chars");

				view.print( model.advancedSearch("name=ni"));
				view.print("********************");
				view.print("name contaisn jj number of boats = 3");
				view.print( model.advancedSearch("name=jj;numberofBoats=3"));
				view.print("********************");
				break;
			default:
				view.print("Illegal command");
			}
			
		}
	}

}
