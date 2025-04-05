package model;

import java.util.ArrayList;
import java.util.List;

public class Product {
	private String prudoct_name;
    private int cost_price;
    private int selling_price;
    private int stock;
    private int product_wegith;
    private List<Order> orders;

    // Constructor
    public Product(String name, int costPrice, int sellingPrice, int stock, int product_wegith) {
        this.prudoct_name = name;
        this.cost_price = costPrice;
        this.selling_price = sellingPrice;
        this.stock = stock;
        this.product_wegith = product_wegith;
        this.orders = new ArrayList<>();
    }

	public String getPrudoct_name() {
		return prudoct_name;
	}

	public int getCost_price() {
		return cost_price;
	}

	public int getSelling_price() {
		return selling_price;
	}

	public int getStock() {
		return stock;
	}

	public void setPrudoct_name(String prudoct_name) {
		this.prudoct_name = prudoct_name;
	}

	public void setCost_price(int cost_price) {
		this.cost_price = cost_price;
	}

	public void setSelling_price(int selling_price) {
		this.selling_price = selling_price;
	}

	public boolean setStock(int stock) {
		this.stock = stock;
		return true;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public int getProduct_weight() {
		return product_wegith;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("\nProduct name = " + prudoct_name + "\n Cost price = " + cost_price + "\n selling price = " + selling_price
				+ "\n stock = " + stock + "\n product wegith = " + product_wegith + "\n");
		if (!orders.isEmpty()) {
			sb.append("No orders for this product\n");
			return sb.toString();
		}	
		return sb.toString();
	}

	public boolean addOrder(Order order) {
		return this.orders.add(order);		
	}

	public void removeOrder(Order order) {
		// TODO Auto-generated method stub
		int quantity = order.getQuantity();
		this.stock += quantity;
		this.orders.remove(order);		
	}
	

}
