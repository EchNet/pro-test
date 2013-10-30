package protest.entities;

import java.math.BigDecimal;
import java.util.Map;

// A map of product IDs to discount prices.
public class Promotion
{
	String id;
	String code;
	Map<String,BigDecimal> discounts;

	public String getId()
	{
		return id;
	}

	public String getCode()
	{
		return code;
	}

	public Map<String,BigDecimal> getDiscounts()
	{
		return discounts;
	}
}
