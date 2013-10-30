package protest.resources;

import java.util.List;
import protest.entities.Customer;
import protest.entities.ExchangeOrder;
import protest.entities.Item;
import protest.entities.Order;

public class MockOrderSystem implements OrderSystem
{
	@Override
	public String createOrder(List<Item> items, Customer customer)
	{
		return "yep";
	}

	@Override
	public String createExchangeOrder(List<Item> items, Order priorOrder, List<Item> exchangeItems)
	{
		return "yep";
	}
}
