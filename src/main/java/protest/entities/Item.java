package protest.entities;

import java.math.BigDecimal;

// A purchased Product, its purchase price, and an optional Promotion.
public class Item
{
	public Product product;
	public BigDecimal price;
	public String promotionCode;
}
