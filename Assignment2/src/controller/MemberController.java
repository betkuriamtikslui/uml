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
			view.clearScreen();
			view.printListOfCommands();
			if (session != null) {
				view.print(session.getName());

			} else if (admin != null) {
				view.loggedInAsSecretary();
				

			}
			view.printListOfCommands();
			
			values = view.getCommand();
			switch (values[0]) {
			case "login":
				if (admin == null && session == null) {
					try {
						if (values[1].equals("secretary")) {
							admin = new Secretary("", "");
						} else {
							System.out.println(values[1]);
							authentificate(values[1]);
						}
					} catch (Exception e) {
						view.invalidParameters();
					}
				} else {
					view.printLoggedInAlready();
				}
				break;
			case "logout":
				if (admin != null || session != null) {
					admin = null;
					session = null;
				} else {
					view.notLoggedin();
				}

				break;
			case "verboseList":
				view.verboseList(model.getMemberList());
				break;
			case "compactList":
				view.compactList(model.getMemberList());
				
				break;

			case "createMember":
				if (values.length == 3 && admin != null) {
					try {
						model.addMember(values[1], values[2]);
						view.memberAddedSuccessfully();	
						store.saveLst(model.getMemberList());

					} catch (Exception e) {
						view.print("unable to add member");
					}
				} else {
					view.invalidParameters();
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
				
				view.boatAdded();
				store.saveLst(model.getMemberList());

				}catch(Exception e){
					view.boatWasNotAdded();
				}
				break;
			case "editMember":
				if(admin != null){
					if(values.length != 5){
						if(model.editMemberInfo(values[1], values[2], values[3], values[4])){
							view.informationWasChanged();
							store.saveLst(model.getMemberList());

						}else{
							view.informationWasNotChanged();
						}
					}else{
						view.invalidParameters();
					}
				}else{
					view.dontHavePermission();
				}
				break;
				
			case "listBoats":
				view.boatList(session.getBoatList());
				break;
				
			case "deleteBoat":
				if(session != null && values.length == 3){
					if(session.deleteBoat(BoatEnum.valueOf(values[2]), Double.parseDouble(values[1]))){
						store.saveLst(model.getMemberList());
						view.operationWasSuccessfull();
					}else{
						view.informationWasNotChanged();
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
				store.saveLst(model.getMemberList());
				view.savingFiles();

				break;
			case "search":
				ArrayList<String> list;
				if(admin != null && values.length ==2){
					list = model.search(values[1]);
					view.print(list);
				}
				
				break;
//			case "queryExample":
//				view.print("********************");
//				
//				view.print("queryExample");
//				
//				view.print("number of obats is 3");
//				view.print( model.advancedSearch("numberofboats=3"));
//				view.print("********************");
//				view.print("name contains ni chars");
//
//				view.print( model.advancedSearch("name=ni"));
//				view.print("********************");
//				view.print("name contaisn jj number of boats = 3");
//				view.print( model.advancedSearch("name=jj;numberofBoats=3"));
//				view.print("********************");
//				break;
			default:
				view.print("Illegal command");
			}
			
		}
	}

}
