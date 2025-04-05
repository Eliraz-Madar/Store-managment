package model;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import data.StoreMemento;

public class Store {
	// Methods to handle product transactions, generate invoices, manage stock, etc.
	// Implement here
	private Set<Customer> customers;
	private Set<Wholesalers> wholesalers;
	private Set<Product> products;
	private List<DeliveryCompany> deliveryCompanies;

	public Store() {
		this.customers = new HashSet<Customer>();
		this.wholesalers = new HashSet<Wholesalers>();
		this.products = new HashSet<Product>();
		this.deliveryCompanies = new ArrayList<DeliveryCompany>();
	}

	public Person getCustomerByMobile(String searchChoice) {
		for (Person customer : customers) {
			if (customer.getMobile().equals(searchChoice)) {
				return customer;
			}
		}
		return null;
	}

	public boolean addCustomerToStore(Person person) {
		if (person == null || person instanceof Wholesalers)
			return false;
		Customer customer = (Customer) person;
		return customers.add(customer);
	}

	public boolean removeCustomerFromStore(String mobile) {
		return customers.remove(getCustomerByMobile(mobile));
	}

	public Set<Customer> getCustomersfromStore() {
		return customers;
	}

	public Product getProductByString(String searchChoice) {
		// TODO Auto-generated method stub
		for (Product product : products) {
			if (product.getPrudoct_name().equals(searchChoice)) {
				return product;
			}
		}
		return null;
	}

	public DeliveryCompany getPriceForDeliveryFromAllCompanyes(Product product) {
		// TODO Auto-generated method stub
		int price = product.getSelling_price() + 100;
		DeliveryCompany deliveryCompanyRes = null;
		for (DeliveryCompany deliveryCompany : deliveryCompanies) {
			for (Order order : product.getOrders()) {
				int temp = deliveryCompany.getPriceForDelivery(order);
				if (temp < price) {
					price = temp;
					deliveryCompanyRes = deliveryCompany;
				}
			}
		}
		return deliveryCompanyRes;
	}

	public List<DeliveryCompany> getDeliveryCompanies() {
		return deliveryCompanies;
	}

	public DeliveryCompany getDeliveryCompanyByName(String name) {
		for (DeliveryCompany deliveryCompany : deliveryCompanies) {
			if (deliveryCompany.getName().equals(name)) {
				return deliveryCompany;
			}
		}
		return null;
	}

	public void setDeliveryCompanies(List<DeliveryCompany> deliveryCompanies) {
		this.deliveryCompanies = deliveryCompanies;
	}

	public String getAllProductsFromStoreString() {
		StringBuilder sb = new StringBuilder();
		for (Product product : products) {
			sb.append(product.toString());
		}
		return sb.toString();
	}

	public Set<Product> getAllProductsFromStore() {
		return products;
	}

	public boolean addProductToStore(Product product) {
		return products.add(product);
	}

	public boolean removeProductFromStore(Product product) {
		return products.remove(product);
	}

	public boolean updateProductStock(String productName, int quantity) {
		Product product = getProductByString(productName);
		if (product != null) {
			product.setStock(quantity);
			return true;
		}
		return false;
	}

	public boolean removeProduct(String name) {
		Product product = getProductByString(name);
		if (product != null) {
			return removeProductFromStore(product);
		}
		return false;
	}

	public boolean addProduct(Product product) {
		return addProductToStore(product);
	}

	public int addOrderForProduct(String mobile, String productName, int quantity, IsExpress isExpress) {
		Person customer = getCustomerByMobile(mobile);
		Product product = getProductByString(productName);
		Order order;
		if (customer == null) {
			return -1;
		} else if (product == null) {
			return -2;
		} else if (quantity > product.getStock()) {
			return -3;
		}
		product.setStock(product.getStock() - quantity);
		order = new Order(product, customer, quantity, isExpress);
		product.addOrder(order);
		return 1;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Store:\n");
		sb.append("- Customers:\n");
		for (Person customer : customers) {
			sb.append(customer.toString() + ".\n");
		}
		sb.append("- Products:\n");
		for (Product product : products) {
			sb.append(product.toString() + "\n");
		}
		sb.append("- Delivery Companies:\n");
		for (DeliveryCompany deliveryCompany : deliveryCompanies) {
			sb.append(deliveryCompany.toString() + "\n");
		}
		return sb.toString();
	}

	public void createSampleData() {
		addPersonToStore(new Customer("052-1234567", "John Doe"));
		addPersonToStore(new Customer("052-7654321", "Jane Doe"));
		addPersonToStore(new Customer("052-1111111", "Alice Wonderland"));
		addPersonToStore(new Customer("052-2222222", "Bob Builder"));
		addPersonToStore(new Customer("052-3333333", "Charlie Chocolate"));
		
		addPersonToStore(new Wholesalers("052-2222222", "Bob Builder"));
		addPersonToStore(new Wholesalers("052-3333333", "Charlie Chocolate"));
		

		addProduct(new Product("Milk", 5, 10, 100, 1));
		addProduct(new SoldInStore("Bread", 3, 5, 200, 1));
		addProduct(new Product("Butter", 7, 15, 50, 1));
		addProduct(new SoldToWholesalers("Eggs", 6, 12, 150, 1));
		addProduct(new Product("Cheese", 10, 20, 100, 1));
		addProduct(new Product("Yogurt", 4, 8, 100, 1));

		deliveryCompanies.add(new DHL_Company(new Person("Mike", "052-4444444")));

		deliveryCompanies.add(new FedEx_Company(new Person("Tom", "052-5555555")));
	}

	public void restoreState(StoreMemento previousState) {
		customers = previousState.getCustomers();
		products = previousState.getProducts();
		deliveryCompanies = previousState.getDeliveryCompanies();
	}

	public void setCustomers(Set<Customer> persons) {
		this.customers = persons;
	}

	public void setProducts(Set<Product> products2) {
		this.products = products2;
	}

	public Order getOrderForProduct(String mobile, String productName) {
		Product product = getProductByString(productName);
		if (product == null)
			return null;
		for (Order order : product.getOrders()) {
			if (order.getPerson().getMobile().equals(mobile) && order.getProduct().getPrudoct_name().equals(productName)) 
				return order;						
		}		
		return null;
	}

	public boolean removeWholesalerFromStore(String mobile) {
		// TODO Auto-generated method stub
		return wholesalers.remove(getPersonByMobile(mobile));		
	}

	private Object getPersonByMobile(String mobile) {
		for (Person person : customers) {
			if (person.getMobile().equals(mobile)) {
				return person;
			}
		}
		for (Person person : wholesalers) {
			if (person.getMobile().equals(mobile)) {
				return person;
			}
		}		
		return null;
	}

	public boolean addPersonToStore(Person person) {
		if (person == null)
			return false;
		if (person instanceof Wholesalers)
			return wholesalers.add((Wholesalers) person);
		else if (person instanceof Person)
			return customers.add((Customer) person);		
		return false;
	}

	public boolean removePersonFromStore(String mobile) {
		// TODO Auto-generated method stub
		Person person = (Person) getPersonByMobile(mobile);
		if (person != null) {
			if (person instanceof Wholesalers)
				return wholesalers.remove(person);
			else if (person instanceof Person)
				return customers.remove(person);
		}
		return false;
	}

	public Set<Wholesalers> getWholesalers() {
		// TODO Auto-generated method stub
		return wholesalers;
	}

	public void setWholesalers(Set<Wholesalers> wholselars) {
		if(wholselars != null)
			this.wholesalers = wholselars;
	}
	
}
