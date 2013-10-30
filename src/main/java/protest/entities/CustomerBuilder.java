package protest.entities;

// Builder for Customer class.
public class CustomerBuilder implements Builder<Customer>
{
	private String id;
	private String name;

	public CustomerBuilder setId(String id)
	{
		this.id = id;
		return this;
	}

	public CustomerBuilder setName(String name)
	{
		this.name = name;
		return this;
	}

	@Override
	public Customer build()
	{
		if (id == null || name == null) {
			throw new IllegalStateException();
		}
		Customer customer = new Customer();
		customer.id = id;
		customer.name = name;
		return customer;
	}
}
