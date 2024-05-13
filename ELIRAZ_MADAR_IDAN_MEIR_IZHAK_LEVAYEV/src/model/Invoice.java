package model;

public class Invoice {
	private String format;
	private Person customer;
	private Order order;

	public Invoice(String format, Order order) {
		this.format = format;
		this.order = order;
		this.customer = order.getPerson();
	}

	public String getInvoice() {
		StringBuilder invoice = new StringBuilder();
		if (format.equals("Customer")) {
			invoice.append("Invoice for: " + customer.getName() + "\n");
			invoice.append("Mobile: " + customer.getMobile() + "\n");
			invoice.append("Product: " + order.getProduct().getPrudoct_name() + "\n");
			invoice.append("Total: " + order.getTotal() + "\n");
		} else if (format.equals("Accountant")) {
			invoice.append("Invoice for: " + format + "\n");
			invoice.append("Total: " + order.getTotal() + "\n");
		} else {
			invoice.append("Invalid format");
		}
		return invoice.toString();
	}

}
