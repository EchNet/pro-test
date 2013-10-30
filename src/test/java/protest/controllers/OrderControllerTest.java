package protest.controllers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import protest.entities.Customer;
import protest.entities.CustomerBuilder;
import protest.entities.ExchangeOrder;
import protest.entities.Item;
import protest.entities.ItemBuilder;
import protest.entities.Order;
import protest.resources.MockCustomerSystem;
import protest.resources.MockOrderSystem;
import protest.resources.MockPricingSystem;
import org.junit.*;
import static org.junit.Assert.*;

public class OrderControllerTest
{
	private OrderController orderController;

	@Before
	public void setup() throws Exception
	{
		orderController = new OrderController(
			new MockOrderSystem(),
			new MockCustomerSystem(),
			new MockPricingSystem());
	}

	@Test
	public void testItemGetsDefaultPriceIfNoPromotion() throws Exception
	{
		Item item = orderController.createItem(MockPricingSystem.UNPROMOTED_PRODUCT, mockCustomer());
		assertNotNull(item);
		assertEquals(MockPricingSystem.UNPROMOTED_PRICE, item.getPrice());
		assertNull(item.getPromotionId());
	}

	@Test
	public void testItemGetsLowestDiscountPriceIfPromotion() throws Exception
	{
		Item item = orderController.createItem(MockPricingSystem.PROMOTED_PRODUCT, mockCustomer());
		assertNotNull(item);
		assertEquals(MockPricingSystem.PROMOTED_PRICE_0, item.getPrice());
		assertEquals(MockPricingSystem.PROMOTION_ID, item.getPromotionId());
	}

	@Test
	public void testCreateOrder() throws Exception
	{
		List<Item> items = new ArrayList<Item>();
		items.add(new ItemBuilder()
			.setProduct(MockPricingSystem.PROMOTED_PRODUCT)
			.setPrice(BigDecimal.valueOf(1.25))
			.build());
		Order order = orderController.createOrder(items, mockCustomer());
		assertNotNull(order);
	}

	@Test
	public void testExchangeItemGetsDefaultPriceIfNoPromotion() throws Exception
	{
		Item item = orderController.createExchangeItem(MockPricingSystem.UNPROMOTED_PRODUCT, mockOrder(), Collections.EMPTY_LIST);
		assertNotNull(item);
		assertEquals(MockPricingSystem.UNPROMOTED_PRICE, item.getPrice());
		assertNull(item.getPromotionId());
	}

	@Test
	public void testExchangeItemGetsLowestDiscountPriceIfPromotion() throws Exception
	{
		Item item = orderController.createExchangeItem(MockPricingSystem.PROMOTED_PRODUCT, mockOrder(), Collections.EMPTY_LIST);
		assertNotNull(item);
		assertEquals(MockPricingSystem.PROMOTED_PRICE_0, item.getPrice());
		assertEquals(MockPricingSystem.PROMOTION_ID, item.getPromotionId());
	}

	@Test
	public void testCreateExchangeOrder() throws Exception
	{
		List<Item> items = new ArrayList<Item>();
		items.add(new ItemBuilder()
			.setProduct(MockPricingSystem.UNPROMOTED_PRODUCT)
			.setPrice(BigDecimal.valueOf(1.25))
			.build());
		ExchangeOrder exchangeOrder =  orderController.createExchangeOrder(items, mockOrder(), mockOrder().getItems());
		assertNotNull(exchangeOrder);
	}

	private Order mockOrder()
	{
		List<Item> returnedItems = new ArrayList<Item>();
		returnedItems.add(new ItemBuilder()
			.setProduct(MockPricingSystem.PROMOTED_PRODUCT)
			.setPrice(BigDecimal.valueOf(1.25))
			.build());
		return orderController.createOrder(returnedItems, mockCustomer());
	}

	private Customer mockCustomer()
	{
		return new CustomerBuilder()
			.setId("mock")
			.setName("mock")
			.build();
	}
}
