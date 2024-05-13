package model;

public class SoldInStore extends Product {
	private final String PRICE_TYPE = "The Price In Shekels";
	
	
	public SoldInStore(String productName, int costPrice, int sellingPrice, int stock, int productWeight) {
		super(productName, costPrice, sellingPrice * 4, stock, productWeight);
	}
	
	public String getPriceType() {
		return PRICE_TYPE;
	}	
	
	@Override
	public String toString() {
		return super.toString() + "\n " + PRICE_TYPE + "\n";
	}
}
