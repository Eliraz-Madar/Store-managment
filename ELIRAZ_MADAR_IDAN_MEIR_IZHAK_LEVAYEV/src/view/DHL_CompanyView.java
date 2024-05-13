package view;

import model.DHL_Company;
import model.Person;

public class DHL_CompanyView {
	public static DHL_Company creatDHL_CompanyFromUserInput() {
		String contactPersonName = InputView.getStringInput("Enter Delivery Company contact person name: ");
		
		String contactPersonMobile = InputView.getMobileFromInput("Enter Delivery Company contact person Mobile: (05x-xxxxxxx) : ");

		return new DHL_Company(new Person(contactPersonName, contactPersonMobile));
	}
}
