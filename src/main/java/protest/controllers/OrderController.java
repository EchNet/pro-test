package protest.controllers;

import java.math.BigDecimal;
import protest.entities.Item;
import protest.entities.Order;
import protest.entities.Promotion;
import protest.resources.CustomerSystem;
import protest.resources.PricingSystem;

public class OrderController
{
	private CustomerSystem customerSystem;
	private PricingSystem pricingSystem;

	public OrderController(CustomerSystem customerSystem, PricingSystem pricingSystem)
	{
		this.customerSystem = customerSystem;
		this.pricingSystem = pricingSystem;
	}

	public void completeOrder(Order order)
	{
		for (Item item : order.items) {
			for (Promotion promotion : pricingSystem.getPromotions()) {
				if (promotion.discounts.containsKey(item.product.id)) {
					BigDecimal price = promotion.discounts.get(item.product.id);
					if (item.price == null || item.price.compareTo(price) > 0) {
						item.price = price;
						item.promotionCode = promotion.code;
					}
				}
			}
			if (item.price == null) {
				item.price = pricingSystem.getPrice(item.product.id);
			}
		}
	}
}
