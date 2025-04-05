package view;

import model.DeliveryCompany;
import model.Product;
import model.SoldTroughWebsite;
import model.Store;

public class SoldTroughWebsiteView {
	
	public static String getDeliveryOption(Store store, DeliveryCompany deliveryCompany) {
		String productName = InputView.getStringInput("Enter product name: ");
		Product product = store.getProductByString(productName);
		if (product instanceof SoldTroughWebsite) {
			return "Delivery company: " + deliveryCompany.toString();
		} else {
			return "Product is not sold through website";
		}
	}

}
