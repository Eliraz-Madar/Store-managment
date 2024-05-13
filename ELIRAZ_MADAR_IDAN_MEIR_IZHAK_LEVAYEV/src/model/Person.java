package model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Person {
	private String name;
	private String mobile;

	// Constructor
	public Person(String name, String mobile) {
		this.name = name;
		this.mobile = mobile;
	}

	public String getName() {
		return name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean setMobile(String mobile) {
		if (isValidMobile(mobile)) {
			this.mobile = mobile;
			return true;
		} else
			System.out.println("Not a Valid mobile number!");

		return false;
	}

	public static boolean isValidMobile(String mobile) {
		try {
			// Define the regex pattern
			String pattern = "^05\\d{1}-\\d{7}$";

			// Create a Pattern object
			Pattern regex = Pattern.compile(pattern);

			// Create a Matcher object
			Matcher matcher = regex.matcher(mobile);

			// Perform matching
			return matcher.matches();
		} catch (Exception e) {
			System.out.println("An error occurred while validating mobile number: " + e.getMessage());
			return false;
		}

	}

	/*public Person getCustomerByName(Store store, String searchChoice) {
		for (Person customer : store.getCustomersfromStore()) {
			if (customer.getName().equals(searchChoice)) {
				return customer;
			}
		}
		return null;
	}*/
	
	@Override
	public String toString() {
		return "Name: " + name + "\nMobile: " + mobile + "\n";
	}
}
