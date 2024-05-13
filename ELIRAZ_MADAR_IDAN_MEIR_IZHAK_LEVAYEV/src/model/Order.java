package model;

public class Order {

    private Product product;
    private Person person;
    private int quantity;
    private IsExpress isExpress;
    private Invoice invoice;
    private int id = 0;

    // Private constructor to prevent instantiation from outside
    public Order(Product product, Person person, int quantity, IsExpress isExpress) {
        this.person = person;
        this.product = product;
        this.quantity = quantity;
        this.isExpress = isExpress;
        if(person instanceof Customer)
            this.invoice = new Invoice("Customer", this);
        else if (person instanceof Wholesalers)
            this.invoice = new Invoice("Wholesalers", this);
        this.id = id++;
    } 
    
	public boolean isSameId(int id) {
		return this.id == id;
	}

    // Setters for product, customer, and quantity
    public void setProduct(Product product) {
        this.product = product;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // Getters for product, customer, and quantity
    public Product getProduct() {
        return product;
    }

    public Person getPerson() {
        return person;
    }

    public int getQuantity() {
        return quantity;
    }

    public boolean isExpressDelivery() {
        return isExpress == IsExpress.EXPRESS;
    }

    public int getTotal() {
        return product.getSelling_price() * quantity;
    }
    
    @Override
    public String toString() {
        return "Product: " + product.getPrudoct_name() + "\n" + "Customer: " + person.getName() + "\n" + "Quantity: " + quantity + "\n" + "Total amount to pay: " + getTotal() + "\n" + "Express delivery: " + isExpress + "\n" ;
    }
    
    public IsExpress getIsExpress() {
        return this.isExpress;
    }
    
    public String getInvoice() {
        if (invoice != null) {
            return invoice.getInvoice();
        } else {
            return "No invoice available";
        }
    }
}
