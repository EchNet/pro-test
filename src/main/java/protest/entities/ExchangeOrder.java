package protest.entities;

import java.util.List;

// An exchange of one or more items of a prior order for zero or more new items.
public class ExchangeOrder extends Order
{
	String priorOrderId;
	List<Item> returnedItems;

	public String getPriorOrderId()
	{
		return priorOrderId;
	}

	public List<Item> getReturnedItems()
	{
		return returnedItems;
	}
}
