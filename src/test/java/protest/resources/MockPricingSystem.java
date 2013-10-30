package protest.resources;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import protest.entities.Customer;
import protest.entities.Product;
import protest.entities.ProductBuilder;
import protest.entities.Promotion;
import protest.entities.PromotionBuilder;

public class MockPricingSystem implements PricingSystem
{
	public final static String PROMOTION_ID = "promotion";
	public final static Product UNPROMOTED_PRODUCT = mockProduct("unpromoted");
	public final static Product PROMOTED_PRODUCT = mockProduct("promoted");
	public final static BigDecimal UNPROMOTED_PRICE = BigDecimal.valueOf(10);
	public final static BigDecimal PROMOTED_PRICE_0 = BigDecimal.valueOf(8);
	public final static BigDecimal PROMOTED_PRICE_1 = BigDecimal.valueOf(9);

	@Override
	public BigDecimal getPrice(String productId)
	{
		return UNPROMOTED_PRICE;
	}

	@Override
	public List<Promotion> getActivePromotions(Customer customer)
	{
		return Arrays.asList(new Promotion[] { mockPromotion(PROMOTED_PRICE_0), mockPromotion(PROMOTED_PRICE_1) });
	}

	@Override
	public Promotion getPromotion(String promotionCode)
	{
		return mockPromotion(PROMOTED_PRICE_0);
	}

	private Promotion mockPromotion(BigDecimal price)
	{
		return new PromotionBuilder()
			.setId(PROMOTION_ID)
			.setCode(PROMOTION_ID)
			.addDiscount(PROMOTED_PRODUCT.getId(), price)
			.build();
	}

	private static Product mockProduct(String name)
	{
		return new ProductBuilder()
			.setId(name)
			.setSku(name)
			.setDescription(name)
			.build();
	}
}
