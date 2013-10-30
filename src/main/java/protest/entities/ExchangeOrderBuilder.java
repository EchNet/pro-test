package protest.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Builder for the ExchangeOrder class.
public class ExchangeOrderBuilder implements Builder<ExchangeOrder>
{
	private String id;
	private Customer customer;
	private List<Item> items = new ArrayList<Item>();
	private String priorOrderId;
	private List<Item> returnedItems = new ArrayList<Item>();

	public ExchangeOrderBuilder setId(String id)
	{
		this.id = id;
		return this;
	}

	public ExchangeOrderBuilder setCustomer(Customer customer)
	{
		this.customer = customer;
		return this;
	}

	public ExchangeOrderBuilder addItem(Item item)
	{
		items.add(item);
		return this;
	}

	public ExchangeOrderBuilder setItems(List<Item> items)
	{
		this.items = items;
		return this;
	}

	public ExchangeOrderBuilder setPriorOrderId(String priorOrderId)
	{
		this.priorOrderId = priorOrderId;
		return this;
	}

	public ExchangeOrderBuilder addReturnedItem(Item item)
	{
		returnedItems.add(item);
		return this;
	}

	public ExchangeOrderBuilder setReturnedItems(List<Item> returnedItems)
	{
		this.returnedItems = returnedItems;
		return this;
	}

	@Override
	public ExchangeOrder build()
	{
		if (id == null || customer == null || priorOrderId == null || returnedItems.isEmpty()) {
			throw new IllegalStateException();
		}
		ExchangeOrder exchangeOrder = new ExchangeOrder();
		exchangeOrder.id = id;
		exchangeOrder.customer = customer;
		exchangeOrder.items = Collections.unmodifiableList(items);
		exchangeOrder.priorOrderId = priorOrderId;
		exchangeOrder.returnedItems = Collections.unmodifiableList(returnedItems);
		return exchangeOrder;
	}
}
