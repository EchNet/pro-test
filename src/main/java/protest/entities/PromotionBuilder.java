package protest.entities;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

// Builder for class Promotion.
public class PromotionBuilder implements Builder<Promotion>
{
	private String id;
	private String code;
	private Map<String,BigDecimal> discounts = new HashMap<String,BigDecimal>();

	public PromotionBuilder setId(String id)
	{
		this.id = id;
		return this;
	}

	public PromotionBuilder setCode(String code)
	{
		this.code = code;
		return this;
	}

	public PromotionBuilder addDiscount(String productId, BigDecimal price)
	{
		discounts.put(productId, price);
		return this;
	}

	@Override
	public Promotion build()
	{
		if (id == null || code == null || discounts.isEmpty()) {
			throw new IllegalStateException();
		}
		Promotion promotion = new Promotion();
		promotion.id = id;
		promotion.code = code;
		promotion.discounts = Collections.unmodifiableMap(discounts);
		return promotion;
	}
}
