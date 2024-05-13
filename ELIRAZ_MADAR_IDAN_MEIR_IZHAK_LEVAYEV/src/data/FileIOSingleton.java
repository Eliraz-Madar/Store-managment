package data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import model.Customer;
import model.DHL_Company;
import model.DeliveryCompany;
import model.FedEx_Company;
import model.IsExpress;
import model.Order;
import model.Person;
import model.Product;
import model.Store;
import model.Wholesalers;

public class FileIOSingleton {

	private final String customers_path = "customers.txt";
	private final String wholesalers_path = "wholesalers.txt";
	private final String products_path = "products.txt";
	private final String orders_path = "orders.txt";
	private final String invoices_path = "invoices.txt";
	private final String deliveryCompanies_path = "deliveryCompanies.txt";

	private static FileIOSingleton instance = null;

	private File customers_file;
	private File wholesalers_file;
	private File products_file;
	private File orders_file;
	private File invoices_file;
	private File deliveryCompanies_file;

	private Scanner fileReader;

	private FileIOSingleton() {
		try {
			customers_file = new File(customers_path);
			if (!customers_file.exists()) {
				customers_file.createNewFile();
			}
			wholesalers_file = new File(wholesalers_path);
			if (!wholesalers_file.exists()) {
				wholesalers_file.createNewFile();
			}			
			products_file = new File(products_path);
			if (!products_file.exists()) {
				products_file.createNewFile();
			}
			orders_file = new File(orders_path);
			if (!orders_file.exists()) {
				orders_file.createNewFile();
			}
			invoices_file = new File(invoices_path);
			if (!invoices_file.exists()) {
				invoices_file.createNewFile();
			}
			deliveryCompanies_file = new File(deliveryCompanies_path);
			if (!deliveryCompanies_file.exists()) {
				deliveryCompanies_file.createNewFile();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public synchronized static FileIOSingleton getInstance() {
		if (instance == null) {
			instance = new FileIOSingleton();
		}
		return instance;
	}
	
	public void backupStore(Store store) {
		backupCustomer(store.getCustomersfromStore());
		backupWholesalers(store.getWholesalers());
		backupProducts(store.getAllProductsFromStore());
		backupDeliveryCompanies(store.getDeliveryCompanies());
	
	}

	public void backupWholesalers(Set<Wholesalers> wholesalers) {
		// TODO Auto-generated method stub
		try (PrintWriter pw = new PrintWriter(wholesalers_file)) {
			for (Person person : wholesalers) {
				pw.println(person.getMobile());
				pw.println(person.getName());
			}
			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}		
	}
	
	public Set<Wholesalers> restoreWholesalers() {
		Set<Wholesalers> wholesalers = new HashSet<Wholesalers>();
		try {
			this.fileReader = new Scanner(wholesalers_file);
			while (this.fileReader.hasNextLine()) {
				String mobile = fileReader.nextLine();
				if (mobile.equals(""))
					break;
				String name = fileReader.nextLine();
				Wholesalers person = new Wholesalers(name, mobile);
				wholesalers.add(person);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (fileReader != null) {
				fileReader.close();
			}
		}
		return wholesalers;
	}

	public void backupCustomer(Set<Customer> list) {
		try (PrintWriter pw = new PrintWriter(customers_file)) {
			for (Person person : list) {
				pw.println(person.getMobile());
				pw.println(person.getName());
			}
			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public Set<Customer> restorePersons() {
	    Set<Customer> persons = new HashSet<>();
	    try {	 
	    	this.fileReader = new Scanner(customers_file);
	        while (fileReader.hasNextLine()) {
	            String mobile = fileReader.nextLine();
	            if (mobile.isEmpty())
	                break;
	            String name = fileReader.nextLine();
	            Customer person = new Customer(name, mobile);
	            persons.add(person);
	        }
	    } catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
	        if (fileReader != null) {
	            fileReader.close();
	        }
	    }
	    return persons;
	}	

	public void backupProducts(Set<Product> list) {
		try (PrintWriter pw = new PrintWriter(products_file)) {
			for (Product product : list) {
				pw.println(product.getPrudoct_name());
				pw.println(product.getCost_price());
				pw.println(product.getSelling_price());
				pw.println(product.getStock());
				pw.println(product.getProduct_weight());
			}
			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public Set<Product> restoreProducts() {
		Set<Product> products = new HashSet<Product>();
		try {
			this.fileReader = new Scanner(products_file);
			while (this.fileReader.hasNextLine()) {

				String name = fileReader.nextLine();
				if (name.equals(""))
					break;
				String cost = fileReader.nextLine();
				String sell = fileReader.nextLine();
				String stock = fileReader.nextLine();
				String weight = fileReader.nextLine();

				Product product = new Product(name, Integer.parseInt(cost), Integer.parseInt(sell), Integer.parseInt(stock),
						Integer.parseInt(weight));
				products.add(product);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (fileReader != null) {
				fileReader.close();
			}
		}		
		return products;
	}

	public void backupOrders(Set<Product> prducts) {
		try (PrintWriter pw = new PrintWriter(orders_path)) {
			for (Product product : prducts) {
				for (Order order : product.getOrders()) {
					pw.println(order.getQuantity());
					pw.println(order.getIsExpress() == IsExpress.EXPRESS ? 1 : 0);
					pw.println(order.getPerson().getMobile());
					pw.println(order.getPerson().getName());
					pw.println(order.getProduct().getPrudoct_name());
				}
			}
			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Order> restoreOrders(Set<Product> products) {
		ArrayList<Order> orders = new ArrayList<Order>();
		try {
			this.fileReader = new Scanner(orders_file);
			while (this.fileReader.hasNextLine()) {
				String quantity = fileReader.nextLine();
				if (quantity.equals(""))
					break;
				int isExperssStr = Integer.parseInt(fileReader.nextLine());
				String mobile = fileReader.nextLine();
				String personName = fileReader.nextLine();
				String name = fileReader.nextLine();
				for (Product product : products) {
					if (product.getPrudoct_name().equals(name)) {
						product.addOrder(new Order(product, new Person(personName, mobile), Integer.parseInt(quantity),
								isExperssStr == 1 ? IsExpress.EXPRESS : IsExpress.REGULAR));
					}
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
            if (fileReader != null) {
                fileReader.close();
            }
        }
		return orders;
	}
	
	public void backupDeliveryCompanies(List<DeliveryCompany> list) {
		try (PrintWriter pw = new PrintWriter(deliveryCompanies_file)) {
			for (DeliveryCompany deliveryCompany : list) {
				pw.println(deliveryCompany.getName());
				pw.println(deliveryCompany.getContactPerson());
				pw.println(deliveryCompany.getContactPerson().getMobile());
			}
			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<DeliveryCompany> restoreDeliveryCompanies() {
		// TODO Auto-generated method stub
		ArrayList<DeliveryCompany> deliveryCompanies = new ArrayList<DeliveryCompany>();
		this.fileReader = new Scanner(deliveryCompanies_path);
		while (this.fileReader.hasNextLine()) {
			String name = fileReader.nextLine();
			if (name.equals(""))
				break;
			if (name.equals("DHL")) {
				String contactName = fileReader.nextLine();
				String mobile = fileReader.nextLine();
				deliveryCompanies.add(new DHL_Company(new Person(contactName, mobile)));
				continue;
			}else if(name.equals("FedEx")) {
                String contactName = fileReader.nextLine();
                String mobile = fileReader.nextLine();
                deliveryCompanies.add(new FedEx_Company(new Person(contactName, mobile)));
                continue;
            }else {
            	break;
            }
		}
		return deliveryCompanies;
	}

}
