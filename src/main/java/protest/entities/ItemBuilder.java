package protest.entities;

import java.math.BigDecimal;

// Builder for class Item.
public class ItemBuilder implements Builder<Item>
{
	private Product product;
	private BigDecimal price;
	private String promotionId;

	public ItemBuilder setProduct(Product product)
	{
		this.product = product;
		return this;
	}

	public ItemBuilder setPrice(BigDecimal price)
	{
		this.price = price;
		return this;
	}

	public ItemBuilder setPromotionId(String promotionId)
	{
		this.promotionId = promotionId;
		return this;
	}

	@Override
	public Item build()
	{
		if (product == null || price == null) {
			throw new IllegalStateException();
		}
		Item item = new Item();
		item.product = product;
		item.price = price;
		item.promotionId = promotionId;
		return item;
	}
}
