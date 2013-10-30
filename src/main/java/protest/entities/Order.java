package protest.entities;

import java.util.List;
import java.math.BigDecimal;
import java.math.RoundingMode;

// A purchase.  Has a Customer and a list of purchased Items.
public class Order
{
	String id;
	Customer customer;
	List<Item> items;

	public String getId()
	{
		return id;
	}

	public Customer getCustomer()
	{
		return customer;
	}

	public List<Item> getItems()
	{
		return items;
	}

	public BigDecimal getTotalDebit()
	{
		BigDecimal totalDebit = BigDecimal.ZERO;
		for (Item item : items) {
			totalDebit = totalDebit.subtract(item.getPrice());
		}
		return totalDebit.setScale(2, RoundingMode.HALF_UP);
	}
}
