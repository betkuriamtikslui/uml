package People;

import java.util.ArrayList;

import Boat.Boat;
import Boat.BoatEnum;

public class Member extends Person{
	private ArrayList<Boat> boatList = new ArrayList<Boat>();
	public Member(String name, String personalID) {
		super(name, personalID);
		
	}
	public Member(String name, String personalID, String uniqueID) {
		super(name, personalID);
		super.setUniqueID(uniqueID);
		
	}
	public void addBoat(Boat boat){
		boatList.add(boat);
	}
	public ArrayList<Boat> getBoatList(){
		return boatList;
	}
	public boolean deleteBoat(BoatEnum type, double length){
		for(Boat boat: boatList){
			if(type == boat.getType() && boat.getLength() == length){
				boatList.remove(boat);
				return true;
			}
		}
		return false;
	}
	
	public boolean  editBoat(double oldLength, BoatEnum oldType,  double newLength, BoatEnum newType){
		for(Boat boat: boatList){
			if(boat.getLength() == oldLength && boat.getType() == oldType){
				boat.setLength(newLength);
				boat.setType(newType);
				return true;
			}
		}
		return false;
	}
}
