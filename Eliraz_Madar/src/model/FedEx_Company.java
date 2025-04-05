package model;

public class FedEx_Company extends DeliveryCompany {
	private int shipping_fee_express = 50;
	private int shipping_fee_regular = 10;

	public FedEx_Company(Person contactPerson) {
		super(contactPerson);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getPriceForDelivery(Order order) {
		// TODO Auto-generated method stub
		return getPriceForDeliveringAProduct(order.getProduct(), order.getIsExpress());		
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getPriceForDeliveringAProduct(Product product, IsExpress isExpress) {
		// TODO Auto-generated method stub
		int total_price = product.getSelling_price();
		if (isExpress == IsExpress.EXPRESS) {
			total_price += shipping_fee_express * (product.getProduct_weight() % 10);
		} else if (isExpress == IsExpress.REGULAR) {
			total_price += shipping_fee_regular * (product.getProduct_weight() % 10);
		}
		return total_price;
	}
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "FedEx";
	}

}
