package protest.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Builder for the Order class.
public class OrderBuilder implements Builder<Order>
{
	private String id;
	private Customer customer;
	private List<Item> items = new ArrayList<Item>();

	public OrderBuilder setId(String id)
	{
		this.id = id;
		return this;
	}

	public OrderBuilder setCustomer(Customer customer)
	{
		this.customer = customer;
		return this;
	}

	public OrderBuilder addItem(Item item)
	{
		items.add(item);
		return this;
	}

	public OrderBuilder setItems(List<Item> items)
	{
		this.items = items;
		return this;
	}

	@Override
	public Order build()
	{
		if (id == null || customer == null || items.isEmpty()) {
			throw new IllegalStateException();
		}
		Order order = new Order();
		order.id = id;
		order.customer = customer;
		order.items = Collections.unmodifiableList(items);
		return order;
	}
}
