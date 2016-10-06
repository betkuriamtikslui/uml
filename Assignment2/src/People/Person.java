package People;

import java.util.UUID;

public abstract class Person implements Comparable<Person>{
	private String name;
	private String personalID;
	private String uniqueID;
	public Person(String name, String personalID){
		this.name = name;
		this.personalID = personalID;
		uniqueID = UUID.randomUUID().toString();

	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPersonalID() {
		return personalID;
	}
	public void setPersonalID(String personalID) {
		this.personalID = personalID;
	}
	public String getUniqueID() {
		return uniqueID;
	}
	public void setUniqueID(String uniqueID) {
		this.uniqueID = uniqueID;
	}
	@Override
	public int compareTo(Person o) {
		if(o.getName().equals(this.getName()) && this.getPersonalID() ==o.getPersonalID()){
			return 0;
		}else{
			return -1;
		}
	}
	public String getMonth(){
		return personalID.substring(4, 6);
	}
	public String getYear(){
		return personalID.substring(0, 4);
	}
	public String getDay(){
		return personalID.substring(6, 8);
	}
}
