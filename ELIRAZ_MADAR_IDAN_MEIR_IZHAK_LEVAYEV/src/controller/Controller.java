package controller;

import java.util.ArrayList;
import java.util.Set;
import java.util.Stack;

import model.Customer;
import model.DeliveryCompany;
import model.Invoice;
import model.IsExpress;
import model.Order;
import model.Person;
import model.Product;
import model.SoldInStore;
import model.SoldToWholesalers;
import model.SoldTroughWebsite;
import model.Store;
import model.Wholesalers;
import data.FileIOSingleton;
import data.StoreMemento;

public class Controller {
	private Store store;
	private Stack<StoreMemento> mementoStackForRedo;
	private Stack<StoreMemento> mementoStackForUndo;
	private final FileIOSingleton fileIO = FileIOSingleton.getInstance();

	public Controller(Store store) {
		this.store = store;
		this.mementoStackForRedo = new Stack<>();
		this.mementoStackForUndo = new Stack<>();
	}

	public boolean addPerson(Person person) {
		saveToMemento();
		boolean result = store.addPersonToStore(person);
		if (result) {
			this.redoLastAction();
		}
		return result;
	}

	public boolean removeCustomer(String mobile) {
		saveToMemento();
		boolean result = store.removeCustomerFromStore(mobile);
		if (!result) {
			this.redoLastAction();
		}
		return result;
	}

	public boolean removeProduct(String name) {
		saveToMemento();
		boolean result = store.removeProduct(name);
		if (!result) {
			this.redoLastAction();
		}
		return result;
	}

	public int addOrder(String mobile, String productName, int quantity, IsExpress isExpress) {
		saveToMemento();
		int result = store.addOrderForProduct(mobile, productName, quantity, isExpress);
		if (result < 0) {
			this.redoLastAction();
		}
		return result;
	}

	public boolean updateInventory(String productName, int quantity) {
		saveToMemento();
		boolean result = store.updateProductStock(productName, quantity);
		if (!result) {
			this.redoLastAction();
		}
		return result;
	}

	public void runSystemWithSampleData() {
		store.createSampleData();
		saveToMemento();
	}

	private void saveToMemento() {
		mementoStackForUndo.push(new StoreMemento(store.getCustomersfromStore(), store.getWholesalers(),store.getAllProductsFromStore(),
				store.getDeliveryCompanies()));
	}

	public boolean undoLastAction() {
		try {
			if (!mementoStackForUndo.isEmpty()) {
				mementoStackForRedo.push(mementoStackForUndo.pop());
				store.restoreState(mementoStackForRedo.peek());
				return true;
			}
			return false;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean redoLastAction() {
		try {
			if (!mementoStackForRedo.isEmpty()) {
				mementoStackForUndo.push(mementoStackForRedo.pop());
				store.restoreState(mementoStackForUndo.peek());
				return true;
			}
			return false;
		} catch (Exception e) {
			return false;
		}
	}

	public String restoreSystemFromBackup() {
		saveToMemento();
		Set<Customer> custpmers = fileIO.restorePersons();
		Set<Wholesalers> wholselars = fileIO.restoreWholesalers();
		Set<Product> products = fileIO.restoreProducts();
		ArrayList<DeliveryCompany> deliveryCompanies = fileIO.restoreDeliveryCompanies();

		fileIO.restoreOrders(products);// Restore orders for each product	

		// Restore system state
		store.setCustomers(custpmers);
		store.setWholesalers(wholselars);
		store.setProducts(products);
		store.setDeliveryCompanies(deliveryCompanies);
		
		return ("System successfully restored from backup.");
	}

	public void backupSystem() {
		saveToMemento();
		/// Backup system state ///
		fileIO.backupCustomer(store.getCustomersfromStore());
		fileIO.backupWholesalers(store.getWholesalers());
		fileIO.backupProducts(store.getAllProductsFromStore());
		fileIO.backupOrders(store.getAllProductsFromStore());
		fileIO.backupDeliveryCompanies(store.getDeliveryCompanies());

		System.out.println("System successfully backed up.");
	}

	public boolean addProductForWebsite(String name, int costPrice, int sellingPrice, int stock,
			String destinationCountry, int product_wegith) {
		Product product = new SoldTroughWebsite(name, costPrice, sellingPrice, stock, destinationCountry,
				product_wegith);
		saveToMemento();
		boolean result = store.addProductToStore(product);
		if (!result) {
			this.redoLastAction();
		}
		return result;
	}

	public boolean addProductForStore(String name, int costPrice, int sellingPrice, int stock, int product_wegith) {
		Product product = new SoldInStore(name, costPrice, sellingPrice, stock, product_wegith);
		saveToMemento();
		boolean result = store.addProductToStore(product);
		if (!result) {
			this.redoLastAction();
		}
		return result;
	}

	public boolean addProductForWholsellers(String name, int costPrice, int sellingPrice, int stock,
			int product_wegith) {
		Product product = new SoldToWholesalers(name, costPrice, sellingPrice, stock, product_wegith);
		saveToMemento();
		boolean result = store.addProductToStore(product);
		if (!result) {
			this.redoLastAction();
		}
		return result;
	}

	public Invoice getInvoiceForOrder(String mobile, String productName, String formet) {
		Invoice invoice = null;
		saveToMemento();
		Order order = store.getOrderForProduct(mobile, productName);
		if (order != null) {
			invoice = new Invoice(formet, order);
		}else
			this.redoLastAction();
		return invoice;
	}	

	public boolean removePersonFromStore(String mobile) {
		// TODO Auto-generated method stub		
		saveToMemento();
		if (!store.removePersonFromStore(mobile)) {
			this.redoLastAction();
			return false;
		}
		return true;
	}

	public String displayStore() {
		return store.toString();
	}
	
	public String displayProductDetails(String productName) {
		Product product = store.getProductByString(productName);
		if (product == null) {
			return "Product not found.";
		}
		return product.toString();
	}
	
	public String displayAllProducts() {
		return store.getAllProductsFromStoreString();
	}
	
	
	public String displayAllCustomers() {
		StringBuilder sb = new StringBuilder();
		if (store.getCustomersfromStore().size() == 0) {
			sb.append("No customers found.");
		} else {
			for (Person person : store.getCustomersfromStore()) {
				sb.append(person.toString());
			}
		}
		return sb.toString();
	}
	
	
	public Store getStore() {
		return store;
	}
	
	
	public String displayAllOrders() {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		for (Product product : store.getAllProductsFromStore()) {
			for (Order order : product.getOrders()) {
				sb.append(order.toString());
			}
		}
		return sb.toString();
	}

	
	public String displayAllWholesalers() {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		for (Person person : store.getWholesalers()) {
			sb.append(person.toString());
		}
		return sb.toString();		
	}

	
	public boolean setProductPrice(String name, int price) {
		// TODO Auto-generated method stub
		saveToMemento();
		Product product = store.getProductByString(name);
		if (product != null) {
			product.setSelling_price(price);
			return true;
		}else
			this.redoLastAction();
		return false;
	}

	public boolean setProductName(String name, String newName) {
		// TODO Auto-generated method stub
		saveToMemento();
		Product product = store.getProductByString(name);
		if (product != null) {
			product.setPrudoct_name(newName);
			return true;
		}else
			this.redoLastAction();
		return false;
	}

	public Product getProductFromStore(String name) {
		// TODO Auto-generated method stub
		return store.getProductByString(name);		
	}


	public boolean setProductQuantity(String name, int quantity) {
		saveToMemento();
		boolean res = getProductFromStore(name).setStock(quantity);
		if(!res) 
			this.redoLastAction();
		
		return res;
	}

	public boolean removeOrder(int orderNumber) {
		// TODO Auto-generated method stub
		
		saveToMemento();
		for (Product product : store.getAllProductsFromStore()) {
			for (Order order : product.getOrders()) {
				if (order.isSameId(orderNumber)) {
					product.removeOrder(order);
					return true;
				}
			}
		}
		this.redoLastAction();
		return false;
	}

	public Order getOrder(int orderNumber) {
		// TODO Auto-generated method stub
		for (Product product : store.getAllProductsFromStore()) {
			for (Order order : product.getOrders()) {
				if (order.isSameId(orderNumber)) {
					return order;
				}
			}
		}
		return null;
	}

	public void setPersonForDeliveryCompany(DeliveryCompany deliveryCompany, Person contactPerson) {
		// TODO Auto-generated method stub		
		saveToMemento();		
		deliveryCompany.setContactPerson(contactPerson);
	}

	public void setNameForDelivaryCompanyPerson(DeliveryCompany deliveryCompany, String contactPerson) {
		// TODO Auto-generated method stub
		saveToMemento();		
		deliveryCompany.getContactPerson().setName(contactPerson);
	}

	public void setMobileForDelivaryCompanyPerson(DeliveryCompany deliveryCompany, String contactPerson) {
		// TODO Auto-generated method stub
		saveToMemento();
		deliveryCompany.getContactPerson().setMobile(contactPerson);
		
	}

}
