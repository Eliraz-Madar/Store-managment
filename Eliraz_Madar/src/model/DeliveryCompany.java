package model;

// Abstract class
public abstract class DeliveryCompany {
    private Person contactPerson;

    // Constructor
    public DeliveryCompany(Person contactPerson) {
        this.contactPerson = contactPerson;
    }

    public Person getContactPerson() {
        return contactPerson;
    }

    // Abstract method to get price for delivery
    public abstract int getPriceForDelivery(Order order);
    
    @Override
	public abstract String toString() ;

	public abstract int getPriceForDeliveringAProduct(Product product,IsExpress isExpress);

	public abstract String getName();
	
	public void setContactPerson(Person contactPerson2) {
		// TODO Auto-generated method stub
		this.contactPerson = contactPerson2;		
	}
}

