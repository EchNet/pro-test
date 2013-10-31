package protest.resources;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import protest.entities.Customer;
import protest.entities.Product;
import protest.entities.ProductBuilder;
import protest.entities.Promotion;
import protest.entities.PromotionBuilder;

public class MockPricingSystem implements PricingSystem
{
	private Map<String,Product> skus = new HashMap<String,Product>();
	private Map<String,BigDecimal> prices = new HashMap<String,BigDecimal>();
	private List<Promotion> promotions = new ArrayList<Promotion>();

	public void addSku(String sku, String productId, String description)
	{
		skus.put(sku, new ProductBuilder().setSku(sku).setId(productId).setDescription(description).build());
	}

	public Product getSku(String sku)
	{
		return skus.get(sku);
	}

	public void addPrice(String productId, BigDecimal price)
	{
		prices.put(productId, price);
	}

	public void addPromotion(String productId, BigDecimal price)
	{
		Promotion promotion = new PromotionBuilder()
			.setId(productId + "-promotion-" + promotions.size())
			.setCode("promo")
			.addDiscount(productId, price)
			.build();
		promotions.add(promotion);
	}

	public void clearPromotions()
	{
		promotions.clear();
	}

	@Override
	public BigDecimal getPrice(String productId)
	{
		return prices.get(productId);
	}

	@Override
	public List<Promotion> getActivePromotions(Customer customer)
	{
		return promotions;
	}

	@Override
	public Promotion getPromotion(String promotionCode)
	{
		return null;
	}
}
