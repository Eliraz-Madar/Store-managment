package view;

import controller.Controller;
import model.Customer;
import model.Person;
import model.Store;
import model.Wholesalers;

public class PersonView {
	private Controller controller;

	public PersonView(Controller controller) {
		this.controller = controller;
	}

	private void printCustomerMenu() {
		System.out.println("Customer menu");
		System.out.println("1. Create a new customer");
		System.out.println("2. Set customer mobile");
		System.out.println("3. Set customer name");
		System.out.println("4. Get customer from store");
		System.out.println("5. Remove customer from store");
		System.out.println("6. Add wholesaler to store");
		System.out.println("7. Remove wholesaler to store");
		System.out.println("8. Display all customers");
		System.out.println("9. Display all wholesalers");
		System.out.println("E. Return to main menu");
	}

	public String executeCustomerMenu() {
		String choice = "";
		do {
			printCustomerMenu();
			choice = InputView.getStringInput("Enter your choice: ");
			switch (choice) {
			case "1":
				createCustomerFromUserInput();
				break;
			case "2":
				setCustomerMobileFromUserInput();
				break;
			case "3":
				setCustomerNameFromUserInput();
				break;
			case "4":
				removePersonFromStore();
				break;
			case "5":
				getCustomerFromStoreWithUserInput();
				break;
			case "6":
				addWholesalerToStore();
				break;
			case "7":
				removeWholesalerFromStore();
				break;
			case "8":
				displayAllCustomers();
				break;
			case "9":
				dispayAllwholesalers();
				break;
			case "E": case "e":
				return choice;

			default:
				System.out.println("Invalid choice");
			}
		} while (true);
	}

	private void dispayAllwholesalers() {
		// TODO Auto-generated method stub
		System.out.println(controller.displayAllWholesalers());
	}

	private void removePersonFromStore() {
		// TODO Auto-generated method stub
		String mobile = InputView.getMobileFromInput("Enter customer mobile: ");
		if (controller.removePersonFromStore(mobile)) {
			System.out.println("Customer removed successfully");
		} else {
			System.out.println("Customer not found!");
		}		
	}

	private void removeWholesalerFromStore() {
		String mobile = InputView.getMobileFromInput("Enter wholesaler mobile: ");
		if (controller.removePersonFromStore(mobile)) {
			System.out.println("Wholesaler removed successfully");
		} else {
			System.out.println("Wholesaler not found!");
		}
	}

	private void addWholesalerToStore() {
		// TODO Auto-generated method stub
		String name = InputView.getStringInput("Enter wholesaler name: ");
		String mobile = InputView.getMobileFromInput("Enter wholesaler mobile: ");
		Wholesalers wholesaler = new Wholesalers(name, mobile);
		if (controller.addPerson(wholesaler)) {
			System.out.println("Wholesaler added successfully");
		} else {
			System.out.println("Wholesaler not added!");
		}
		
	}

	private void createCustomerFromUserInput() {
		String name = InputView.getStringInput("Enter customer name: ");
		String mobile = InputView.getStringInput("Enter customer mobile: ");
		Person customer = new Customer(name, mobile);
		controller.addPerson(customer);
	}

	private Person getCustomerByMobileFromUserInput(Store store) {
		String mobile = InputView.getStringInput("Enter customer mobile: ");
		if (!Person.isValidMobile(mobile)) {
			System.out.println("Invalid mobile number");
			return null;
		}
		Person customer = store.getCustomerByMobile(mobile);
		if (customer == null) {
			System.out.println("Customer not found!");
			return null;
		}
		return customer;
	}

	private boolean setCustomerMobileFromUserInput() {
		Person customer = getCustomerByMobileFromUserInput(controller.getStore());
		if (customer == null) {
			System.out.println("Customer not found!");
			return false;
		}
		String mobile = InputView.getStringInput("Enter new mobile: ");
		if (!Person.isValidMobile(mobile)) {
			System.out.println("Invalid mobile number");
			return false;
		}
		customer.setMobile(mobile);
		return true;
	}

	private boolean setCustomerNameFromUserInput() {
		Person customer = getCustomerFromStoreWithUserInput();
		if (customer == null) {
			System.out.println("Customer not found!");
			return false;
		}
		String name = InputView.getStringInput("Enter new name: ");
		customer.setName(name);
		return true;
	}

	private Person getCustomerFromStoreWithUserInput() {
		return StoreView.getCustomerByMobileFromUserInput(controller.getStore());
	}
	
	private void displayAllCustomers() {
		System.out.println(controller.displayAllCustomers());
	}

}
