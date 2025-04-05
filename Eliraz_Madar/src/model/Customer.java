package model;

public class Customer extends Person{

	public Customer(String name, String mobile) {
		super(name, mobile);
	}

	@Override
	public String toString() {
		return "\nCustomer \n" + super.toString();
	}
}
