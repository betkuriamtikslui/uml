package Storage;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;

import Boat.Boat;
import Boat.BoatEnum;
import People.Member;

public class Storage  {
	/*
	 * name-id-uniqueID>boatLengt-hboatType_boatLengt-hboatType
	 */
	String location = "filename.txt";
	
	
	public ArrayList<Member> getMemberList(){
		try {
			String member;
			ArrayList<Member> memberList = new ArrayList<Member>();
			Scanner in = new Scanner(new FileReader(location));
			while(in.hasNext()){
				member = in.nextLine();
				String[] memberInfo = member.split(">")[0].split("#");	//gets name id and uniqueID
				System.out.println(member.split(">").length);
				Member m = new Member(memberInfo[0], memberInfo[1], memberInfo[2]);
				memberList.add(m);
				String[] boats = member.split(">");
				if(boats.length >1){									//if member has boats
					boats = boats[1].split("_");
					String[] boatInfo;
					for(String boat: boats){
						boatInfo = boat.split("#");
						try{
							m.addBoat(new Boat(Double.parseDouble(boatInfo[0]), BoatEnum.valueOf(boatInfo[1])));
						}catch(Exception e){
							System.out.println("unable to add");
						}
					}
				}
			
			}
			return memberList;
		} catch (FileNotFoundException e) {
			return null;
		}
	}
	
	public void saveLst(ArrayList<Member> list){
		try {
			PrintWriter writer = new PrintWriter(location, "UTF-8");
			for(Member member: list){
				String memberInfo = member.getName()+"#"+member.getPersonalID()+"#"+member.getUniqueID()+">";
				for(Boat b: member.getBoatList()){
					memberInfo+=b.getLength()+"#"+b.getType().toString()+"_";
				}
				writer.println(memberInfo);
				writer.flush();
			}
			writer.close();
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
