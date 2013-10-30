package protest.entities;

import java.math.BigDecimal;

// A purchased Product, its purchase price, and an optional Promotion.
public class Item
{
	Product product;
	BigDecimal price;
	String promotionId;

	public Product getProduct()
	{
		return product;
	}

	public BigDecimal getPrice()
	{
		return price;
	}

	public String getPromotionId()
	{
		return promotionId;
	}
}
