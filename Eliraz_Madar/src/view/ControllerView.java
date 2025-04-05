package view;

import controller.Controller;

public class ControllerView {

	protected Controller controller;

	public ControllerView(Controller controller) {
		this.controller = controller;
		showMenu();
	}

	private void printMenu() {
		System.out.println("\nMain Menu:");
		System.out.println("1. Run the system with sample data");
		System.out.println("2. Get Product menu");
		System.out.println("3. Get Person menu");
		System.out.println("4. Get Order menu");	
		System.out.println("5. Get Delivery Company menu");
		System.out.println("6. Undo last action");
		System.out.println("7. Redo last action");
		System.out.println("8. Save all changes and backup");
		System.out.println("9. Restore system from backup");
		System.out.println("E. Exit");
	}

	public void showMenu() {
		String choice = "";
		while (choice != "E" || choice != "e") {
			printMenu();
			choice = InputView.getStringInput("Enter your choice: ");
			switch (choice) {
			case "1":
				runSystemWithSampleData();
				break;
			case "2":
				productMenu();
				break;
			case "3":
				personMenu();
				break;
			case "4":
				orderMenu();
				break;
			case "5":
				deliveryCompanyMenu();
				break;
			case "6":
				undoLastAction();
				break;
			case "7":
				redoLastAction();
				break;
			case "8":
				backupSystem();
				break;
			case "9":
				restoreSystemFromBackup();
				break;
			case "E": case "e":
				System.out.println("Exiting...");
				return;
			default:
				System.out.println("Invalid input. Please try again.");
			}
		}
	}

	private void deliveryCompanyMenu() {
		DeliveryCompanyView deliveryCompanyView = new DeliveryCompanyView(controller);
		deliveryCompanyView.executeDeliveryCompanyMenu();
	}

	private void runSystemWithSampleData() {
		controller.runSystemWithSampleData();
	}	

	private void undoLastAction() {
		if (controller.undoLastAction()) {
			System.out.println("Undo successful.");
		} else {
			System.out.println("Nothing to undo.");
		}
	}

	private void redoLastAction() {
		if (controller.redoLastAction()) {
			System.out.println("Redo successful.");
		} else {
			System.out.println("Nothing to redo.");
		}
	}

	private void restoreSystemFromBackup() {
		controller.restoreSystemFromBackup();
	}

	private void backupSystem() {
		controller.backupSystem();
	}

	private void personMenu() {	
		PersonView customerView = new PersonView(controller);
		customerView.executeCustomerMenu();
	}	
	
	private void productMenu() {
		ProductView productView = new ProductView(controller);
		productView.executeProductMenu();
	}
	
	private void orderMenu() {
		OrderView orderView = new OrderView(controller);
		orderView.executeOrderMenu();
	}	
}
