package Boat;


public class Boat {
	private BoatEnum type;
	private double length;


	
	public Boat(double length, BoatEnum type) {
		this.type = type;
		this.length = length;

	}

	public BoatEnum getType() {
		return type;
	}

	public void setType(BoatEnum type) {
		this.type = type;
	}

	public double getLength() {
		return length;
	}

	public void setLength(double length) {
		this.length = length;
	}

	public String toString() {
		String s = "Boats type is" + type + ",and boats length is:" + length;
		return s;
	}
}
