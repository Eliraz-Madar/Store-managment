package model;

public class SoldToWholesalers extends Product{

	public SoldToWholesalers(String name, int costPrice, int sellingPrice, int stock,int product_wegith) {
		super(name, costPrice, sellingPrice, stock, product_wegith);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Order order : this.getOrders()) {
			sb.append(order.getInvoice());
		}
		return sb.toString();
	}
	

}
