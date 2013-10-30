package protest.resources;

import java.math.BigDecimal;
import java.util.List;
import protest.entities.Customer;
import protest.entities.Promotion;

// The pricing system keeps track of prices and promotions.
public interface PricingSystem
{
	public BigDecimal getPrice(String productId);
	public List<Promotion> getActivePromotions(Customer customer);
	public Promotion getPromotion(String promotionCode);
}
