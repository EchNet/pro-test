package protest.resources;

import java.math.BigDecimal;
import java.util.List;
import protest.entities.Promotion;

public interface PricingSystem
{
	public BigDecimal getPrice(String productId);
	public List<Promotion> getPromotions();
}
