package data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import model.Product;
import model.Wholesalers;
import model.Customer;
import model.DeliveryCompany;


public class StoreMemento {
    private Set<Customer> customers;
    private Set<Wholesalers> wholesalers;
    private Set<Product> products;
    private List<DeliveryCompany> deliveryCompanies;
    			
    public StoreMemento(Set<Customer> customers,Set<Wholesalers> wholesalers ,Set<Product> products, List<DeliveryCompany> deliveryCompanies) {
        this.customers = new HashSet<>(customers);
        this.wholesalers = new HashSet<>(wholesalers);
        this.products = new HashSet<>(products);
        this.deliveryCompanies = new ArrayList<>(deliveryCompanies);
    }
    
	public Set<Wholesalers> getWholesalers() {
		return wholesalers;
	}

    public Set<Customer> getCustomers() {
        return customers;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public List<DeliveryCompany> getDeliveryCompanies() {
        return deliveryCompanies;
    }
}
