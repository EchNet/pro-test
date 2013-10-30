package protest.resources;

import java.util.List;
import protest.entities.Customer;
import protest.entities.ExchangeOrder;
import protest.entities.Item;
import protest.entities.Order;

// The order system keeps track of orders.
public interface OrderSystem
{
	public String createOrder(List<Item> items, Customer customer);
	public String createExchangeOrder(List<Item> items, Order priorOrder, List<Item> exchangeItems);
}
