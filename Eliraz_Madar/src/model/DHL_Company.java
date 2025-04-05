package model;


public class DHL_Company extends DeliveryCompany{
	private final int DELIVERY_FEE = 100;
	private int tax_fee = 20;
	
	public DHL_Company(Person contactPerson) {
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

	@Override
	public int getPriceForDeliveringAProduct(Product product,IsExpress isExpress) {
		int total_price = product.getCost_price();
		if (isExpress == IsExpress.EXPRESS) {
			total_price += DELIVERY_FEE + tax_fee;
		} else if (isExpress == IsExpress.REGULAR) {
			int delivaryFee = product.getCost_price() * 10 / 100;
			if (delivaryFee > 100) {
				delivaryFee = 100;
			}
			total_price += delivaryFee;
		}
		return total_price;
	}
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "DHL";
	}

	
	
}
