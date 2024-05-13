package model;

public class Wholesalers extends Person{

	public Wholesalers(String name, String mobile) {
		super(name, mobile);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return "\nWholesalers \n" + super.toString();
	}

}
