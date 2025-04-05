package view;
import controller.Controller;
import model.*;

public class OrderView {
	private Controller controller;
	
	public OrderView(Controller controller) {
		this.controller = controller;
	}
	
	private void printOrderMenu() {
		System.out.println("Order menu:");
		System.out.println("1. Add order");
		System.out.println("2. Remove order");
		System.out.println("3. Update order");
		System.out.println("4. View orders for a product");
		System.out.println("5. Show all orders");
		System.out.println("6. Display All Orders");
		System.out.println("'E'. Exit");	
	}
	
	public void executeOrderMenu() {
		String choice = "";
		while (!choice.equals("5")) {
			printOrderMenu();
			choice = InputView.getStringInput("Enter your choice: ");
			switch (choice) {
			case "1":
				addOrderForProduct();
				break;
			case "2":
				removeOrder();
				break;
			case "3":
				updateOrder();
				break;
			case "4":
				viewOrders();
				break;
			case "5":
				getInvoiceForOrder();
				break;
			case"6":
				displayAllOrders();
				break;
			case "E": case "e":
				break;
			default:
				System.out.println("Invalid choice");
				break;
			}
		}
	}
	
	private void addOrderForProduct() {
		String mobile;
		String productName;
		int quantity;
		IsExpress isExpress;

		mobile = InputView.getMobileFromInput("Enter mobile number of exissting customer: ");
		productName = InputView.getStringInput("Enter product name: ");
		quantity = InputView.getIntInput("Enter quantity: ");
		isExpress = InputView.getIsExpressInput("Is express delivery? (Y/N): ");
		int res = controller.addOrder(mobile, productName, quantity, isExpress);

		if (res == -1)
			System.out.println("No customer found with this mobile number.");
		else if (res == -2)
			System.out.println("No product found with this name.");
		else if (res == -3)
			System.out.println("Not enough stock for this product.");
		else
			System.out.println("Order added successfully.");
	}

	private void removeOrder() {
		int orderNumber = InputView.getIntInput("Enter order id: ");
		if (controller.removeOrder(orderNumber)) {
			System.out.println("Order removed successfully");
		} else {
			System.out.println("Order not found!");
		}
	}
	
	private void updateOrder() {
		int orderNumber = InputView.getIntInput("Enter order id: ");
		Order order = controller.getOrder(orderNumber);
		if (order == null) {
			System.out.println("Order not found!");
			return;
		}
		if (setOrderAmountFromUserInput(order)) {
			System.out.println("Order updated successfully");
		}
	}
	
	private void viewOrders() {
		String productName = InputView.getStringInput("Enter product name: ");
		System.out.println(controller.displayProductDetails(productName));
	}
	
	private static IsExpress getIsExpressFromUserInput() {
        IsExpress isExpress = InputView.getIsExpressInput("Is express delivery? (y/n): ");
        return isExpress;
    }
	
	public static Order createOrderFromUser(Store store) {
		Product product = StoreView.getProductUserInput(store);
		if (product == null) {
			return null;
		}
		Person customer = StoreView.getCustomerByMobileFromUserInput(store);
		int quantity = getOrderAmountFromUserInput();
		IsExpress isExpress = getIsExpressFromUserInput();
		Order order = new Order(product, customer, quantity, isExpress);
		return order;
	}
	
	private static int getOrderAmountFromUserInput() {
		int orderAmount = InputView.getIntInput("Enter order amount: ");
		return orderAmount;
	}	
		
	private boolean setOrderAmountFromUserInput(Order order) {
		// Get the user input
		// Set the amount for the order object
		int orderAmount = getOrderAmountFromUserInput();
		order.setQuantity(orderAmount);
		printOrderDetails(order);
		return true;
	}
	
	private void printOrderDetails(Order order) {
		System.out.println(order);
	}

	private void getInvoiceForOrder() {
		String mobile;
		String productName;
		int format;
		Invoice invoice = null ;

		mobile = InputView.getMobileFromInput("Enter mobile number: ");
		productName = InputView.getStringInput("Enter product name: ");
		format = InputView.getIntInput("Enter format for invoice (1: Customer, 2: whoulseres / acountent): ");
		if(format == 1)
			invoice = controller.getInvoiceForOrder(mobile, productName, "Customer");
		else if (format == 2)
			invoice = controller.getInvoiceForOrder(mobile, productName, "Acountent");
		else
			System.out.println("Invalid format.");
		
		if (invoice != null) {
			System.out.println(invoice.getInvoice());
		} else {
			System.out.println("Invoice not found.");
		}
	}
	
	private void displayAllOrders() {
		System.out.println(controller.displayAllOrders());
	}

}
