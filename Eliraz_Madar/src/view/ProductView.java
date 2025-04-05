package view;

import controller.Controller;
import model.Product;

public class ProductView {
	private Controller controller;

	public ProductView(Controller controller) {
		this.controller = controller;
	}

	private void printProductMenu() {
		System.out.println("Product menu");
		System.out.println("1. Create a new product");
		System.out.println("2. Set product price");
		System.out.println("3. Set product name");
		System.out.println("4. Get product from store");
		System.out.println("5. Remove product from store");
		System.out.println("6. Display all products");
		System.out.println("7. Set product quantity");
		System.out.println("'E'. Return to main menu");
	}

	public String executeProductMenu() {
		String choice = "0";
		do {
			printProductMenu();
			choice = InputView.getStringInput("Enter your choice: ");
			switch (choice) {
			case "1":
				createProductFromUser();
				break;
			case "2":
				setProductPriceFromUserInput();
				break;
			case "3":
				setProductNameFromUserInput();
				break;
			case "4":
				getProductFromStoreWithUserInput();
				break;
			case "5":
				removeProductFromStore();
				break;
			case "6":
				displayAllProducts();
				break;
			case "7":
				setProductQuantityFromUserInput();
				break;
			case "e":
			case "E":
				return choice;
			}
		} while (choice != "e" || choice != "E");
		return choice;
	}

	private void createProductFromUser() {
		String name;
		String productSellType;
		int costPrice;
		int sellingPrice;
		int stock;
		int product_wegith;

		name = InputView.getStringInput("Enter product name: ");
		costPrice = InputView.getIntInput("Enter cost price: ");
		sellingPrice = InputView.getIntInput("Enter selling price: ");
		stock = InputView.getIntInput("Enter stock: ");
		product_wegith = InputView.getIntInput("Enter product weight: ");
		productSellType = InputView.getProductTypeInput("Enter product type: ");

		boolean result = false;

		if (productSellType.equals("1")) {
			String destinationCountry = InputView.getStringInput("Enter destination country: ");
			result = controller.addProductForWebsite(name, costPrice, sellingPrice, stock, destinationCountry,
					product_wegith);
		} else if (productSellType.equals("2")) {
			result = controller.addProductForStore(name, costPrice, sellingPrice, stock, product_wegith);
		} else if (productSellType.equals("3")) {
			result = controller.addProductForWholsellers(name, costPrice, sellingPrice, stock, product_wegith);
		}

		if (result)
			System.out.println("Product added successfully.");
		else
			System.out.println("Product not added successfully.");
	}

	private void setProductPriceFromUserInput() {
		// TODO Auto-generated method stub
		String name = InputView.getStringInput("Enter product name: ");
		int price = InputView.getIntInput("Enter product price: ");

		if (controller.setProductPrice(name, price)) {
			System.out.println("Product price updated successfully");
		} else {
			System.out.println("Product not found!");
		}
	}

	private void setProductNameFromUserInput() {
		// TODO Auto-generated method stub
		String name = InputView.getStringInput("Enter product name: ");
		String newName = InputView.getStringInput("Enter new product name: ");

		if (controller.setProductName(name, newName)) {
			System.out.println("Product name updated successfully");
		} else {
			System.out.println("Product not found!");
		}
	}

	private void removeProductFromStore() {
		String name = InputView.getStringInput("Enter product name: ");
		if (controller.removeProduct(name)) {
			System.out.println("Product removed successfully");
		} else {
			System.out.println("Product not found!");
		}
	}

	private void getProductFromStoreWithUserInput() {
		String name = InputView.getStringInput("Enter product name: ");
		Product product = controller.getProductFromStore(name);
		if (product != null) {
			System.out.println(product);
		} else {
			System.out.println("Product not found!");
		}
	}

	private void displayAllProducts() {
		System.out.println(controller.getStore().getAllProductsFromStoreString());
	}

	private void setProductQuantityFromUserInput() {
		// TODO Auto-generated method stub
		String name = InputView.getStringInput("Enter product name: ");
		int quantity = InputView.getIntInput("Enter product quantity: ");

		if (controller.setProductQuantity(name, quantity)) {
			System.out.println("Product quantity updated successfully");
		} else {
			System.out.println("Product not found!");
		}

	}

	
}
