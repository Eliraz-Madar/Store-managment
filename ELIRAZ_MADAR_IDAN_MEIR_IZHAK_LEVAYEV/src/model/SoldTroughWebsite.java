package model;

public class SoldTroughWebsite extends Product{
	private String dest_countery;
	private final String PRICE_TYPE = "Price In Dollars";
	private final int priceMultiplier = 4;
	
	public SoldTroughWebsite(String name, int costPrice, int sellingPrice, int stock, String dest_countery,int product_wegith) {
        super(name, costPrice, sellingPrice, stock, product_wegith);
        this.dest_countery = dest_countery;
    }

	public String getDestinationCountry() {
		return dest_countery;
	}

	public void setDestinationCountry(String destinationCountry) {
		this.dest_countery = destinationCountry;
	}

	public boolean makeAnOrder(Store store, SoldTroughWebsite product, int quantity) {
		if (product.getStock() >= quantity) {
			store.updateProductStock(product.getPrudoct_name(), quantity);
			return true;
		}
		return false;
	}
	
	public int getTotalSellingPrice() {
		return this.getSelling_price() * priceMultiplier;
	}
	
	@Override
	public String toString() {
		return super.toString() + "\n Destination country: " + dest_countery + "\n The " + PRICE_TYPE + ":\n The total price in shekels: " + getTotalSellingPrice() + "\n";
	}
		
}
