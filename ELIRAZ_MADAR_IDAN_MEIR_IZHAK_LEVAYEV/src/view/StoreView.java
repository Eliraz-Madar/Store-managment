package view;

import model.Person;
import model.DeliveryCompany;
import model.Product;
import model.Store;

public class StoreView {
	
	public static Person getCustomerByMobileFromUserInput(Store store) {
		// Get the user input
		String searchChoice = InputView.getStringInput("Enter customer mobile: ");
		if (!(Person.isValidMobile(searchChoice))) {
			System.out.println("Invalid mobile number, not a customer!");
			return null;
		}
		// Get the customer from the store
		return store.getCustomerByMobile(searchChoice);
	}

	public static Product getProductUserInput(Store store) {
		// Get the user input
		String searchChoice = InputView.getStringInput("Enter product name: ");
		// Get the product from the store
		return store.getProductByString(searchChoice);
	}

	public static DeliveryCompany chooseDeliveryCompanyForProduct(Store store, Product product) {
		int choice = InputView.getIntInput(
				"Do you want to choose company by lowest price(1) or do you want to see all the options(2): ");
		if (choice == 1) {
			return store.getPriceForDeliveryFromAllCompanyes(product);
		} else if (choice == 2) {
			for (DeliveryCompany deliveryCompany : store.getDeliveryCompanies()) {
				System.out.println(SoldTroughWebsiteView.getDeliveryOption(store, deliveryCompany));
			}
		}
		int i = 1;
		for (DeliveryCompany deliveryCompany : store.getDeliveryCompanies()) {
			System.out.println("Do you want company number: "+ (i++) + deliveryCompany.getName() + " for delivery?");
		}
		int companyNumber = InputView.getIntInput("Enter company number: ");
		return store.getDeliveryCompanies().get(companyNumber - 1);
	}

	
}
