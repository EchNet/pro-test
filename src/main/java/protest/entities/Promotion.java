package protest.entities;

import java.math.BigDecimal;
import java.util.Map;

// A map of product IDs to discount prices.
public class Promotion
{
	public String code;
	public Map<String,BigDecimal> discounts;
}
