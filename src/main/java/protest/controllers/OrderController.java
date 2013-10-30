package protest.controllers;

import java.math.BigDecimal;
import java.util.List;
import protest.entities.Customer;
import protest.entities.ExchangeOrder;
import protest.entities.ExchangeOrderBuilder;
import protest.entities.Item;
import protest.entities.ItemBuilder;
import protest.entities.Order;
import protest.entities.OrderBuilder;
import protest.entities.Product;
import protest.entities.Promotion;
import protest.resources.CustomerSystem;
import protest.resources.OrderSystem;
import protest.resources.PricingSystem;

// OrderController is responsible for applying business rules to Items, Orders and ExchangeOrders.
public class OrderController
{
	private OrderSystem orderSystem;
	private CustomerSystem customerSystem;
	private PricingSystem pricingSystem;

	public OrderController(OrderSystem orderSystem, CustomerSystem customerSystem, PricingSystem pricingSystem)
	{
		this.orderSystem = orderSystem;
		this.customerSystem = customerSystem;
		this.pricingSystem = pricingSystem;
	}

	/**
	 * Build an Item to add to the customer's shopping cart.
	 */
	public Item createItem(Product product, Customer customer)
	{
		// Get default price for the product.
		BigDecimal price = pricingSystem.getPrice(product.getId());

		// Also search active promotions for a discount price.
		String promotionId = null;
		for (Promotion promotion : pricingSystem.getActivePromotions(customer)) {
			if (promotion.getDiscounts().containsKey(product.getId())) {
				BigDecimal discountPrice = promotion.getDiscounts().get(product.getId());
				if (price.compareTo(discountPrice) > 0) {
					price = discountPrice;
					promotionId = promotion.getId();
				}
			}
		}

		return new ItemBuilder()
			.setProduct(product)
			.setPrice(price)
			.setPromotionId(promotionId)
			.build();
	}
	
	/**
	 * Complete the checkout process.
	 */
	public Order createOrder(List<Item> items, Customer customer)
	{
		String orderId = orderSystem.createOrder(items, customer);
		return new OrderBuilder()
			.setId(orderId)
			.setItems(items)
			.setCustomer(customer)
			.build();
	}

	/**
	 * Build an item to add to an exchange order. 
	 */
	public Item createExchangeItem(Product product, Order priorOrder, List<Item> returnedItems)
	{
		return createItem(product, priorOrder.getCustomer());
	}

	/**
	 * Complete the exchange process.
	 */
	public ExchangeOrder createExchangeOrder(List<Item> items, Order priorOrder, List<Item> returnedItems)
	{
		String orderId = orderSystem.createExchangeOrder(items, priorOrder, returnedItems);
		return new ExchangeOrderBuilder()
			.setId(orderId)
			.setItems(items)
			.setCustomer(priorOrder.getCustomer())
			.setPriorOrderId(priorOrder.getId())
			.setReturnedItems(returnedItems)
			.build();
	}
}
