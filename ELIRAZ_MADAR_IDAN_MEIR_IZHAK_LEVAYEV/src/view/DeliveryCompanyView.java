package view;

import controller.Controller;
import model.DeliveryCompany;
import model.Store;

public class DeliveryCompanyView {
	
	private Controller controller;
	
	public DeliveryCompanyView(Controller controller) {
		this.controller = controller;
	}

	public void executeDeliveryCompanyMenu() {
		String choice = "";
		while (choice != "E" || choice != "e") {
			printDeliveryCompanyMenu();
			choice = InputView.getStringInput("Enter your choice: ");
			switch (choice) {
			case "1":
				getDelivaryCompanyFromUserInput();
				break;
			case "2":
				setDeliveryCompanyContactPersonFromUserInput();
				break;
			case "3":
				printAllDeliveryCompanies(controller.getStore());
				break;
			}
		}
	}

	private void printDeliveryCompanyMenu() {
		// TODO Auto-generated method stub
		System.out.println("Delivery Company menu");
		System.out.println("1. Get Delivery Company");
		System.out.println("2. Set Delivery Company contact person");
		System.out.println("3. Display all Delivery Companies");
		System.out.println("E. Return to main menu");		
	}

	public void printDeliveryCompanyDetails(DeliveryCompany deliveryCompany) {
		if (deliveryCompany != null)
			System.out.println(deliveryCompany);
		else
			System.out.println("Delivery Company is null");
	}

	public DeliveryCompany getDelivaryCompanyFromUserInput() {
		System.out.println("Is the company : 1- FedEx, 2- DHL");
		int choice = InputView.getIntInput("Enter your choice: ");
		DeliveryCompany deliveryCompany = null;
		if (choice == 1) {
			deliveryCompany = FedExView.creatFedExFromUserInput();			
		} else if (choice == 2) {
			deliveryCompany = DHL_CompanyView.creatDHL_CompanyFromUserInput();
		} else {
			System.out.println("Invalid choice");
			return null;
		}

		printDeliveryCompanyDetails(deliveryCompany);
		return deliveryCompany;
	}

	public void printAllDeliveryCompanies(Store store) {
		for (DeliveryCompany deliveryCompany : store.getDeliveryCompanies()) {
			System.out.println(deliveryCompany);
		}
	}

	public DeliveryCompany getDelivaryCompanyByNameFromUserInput() {
		// Get the user input
		// Get the delivery company from the store
		// Return the delivery company object
		String companyName = InputView.getStringInput("Enter Delivery Company name: ");
		DeliveryCompany deliveryCompany = controller.getStore().getDeliveryCompanyByName(companyName);
		return deliveryCompany;
	}

	public boolean setDeliveryCompanyContactPersonFromUserInput() {
		// Get the user input
		// Set the contact person for the deliveryCompany object
		printAllDeliveryCompanies(controller.getStore());
		DeliveryCompany deliveryCompany = getDelivaryCompanyByNameFromUserInput();
		if (deliveryCompany == null) {
            System.out.println("Delivery Company not found");
            return false;
        }
		String contactPerson = "";
		int choice = InputView.getIntInput("Enter your choice: ");
		if (choice == 1) {
			contactPerson = InputView.getStringInput("Enter Delivery Company contact person new name: ");
			controller.setNameForDelivaryCompanyPerson(deliveryCompany, contactPerson);
		} else if (choice == 2) {
			contactPerson = InputView.getMobileFromInput("Enter Delivery Company contact person new mobile:");
			controller.setMobileForDelivaryCompanyPerson(deliveryCompany, contactPerson);
		} else {
			System.out.println("Invalid choice");
			return false;
		}			
		printDeliveryCompanyDetails(deliveryCompany);
		return true;
	}
}
