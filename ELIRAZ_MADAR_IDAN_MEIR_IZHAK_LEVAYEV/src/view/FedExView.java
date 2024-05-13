package view;

import model.FedEx_Company;
import model.Person;

public class FedExView {
	public static FedEx_Company creatFedExFromUserInput() {
		String contactPersonName = InputView.getStringInput("Enter Delivery Company contact person name: ");

		String contactPersonMobile = InputView.getMobileFromInput("Enter Delivery Company contact person Mobile: (05x-xxxxxxx) : ")	;

		return new FedEx_Company(new Person(contactPersonName, contactPersonMobile));
	}

}
