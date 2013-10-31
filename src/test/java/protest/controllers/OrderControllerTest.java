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
import protest.entities.Product;
import protest.entities.ProductBuilder;
import protest.resources.MockCustomerSystem;
import protest.resources.MockOrderSystem;
import protest.resources.MockPricingSystem;
import org.junit.*;
import static org.junit.Assert.*;

public class OrderControllerTest
{
	public final static BigDecimal UNPROMOTED_PRICE = BigDecimal.valueOf(10);
	public final static BigDecimal PROMOTED_PRICE_0 = BigDecimal.valueOf(8);
	public final static BigDecimal PROMOTED_PRICE_1 = BigDecimal.valueOf(9);

	private MockOrderSystem orderSystem;
	private MockCustomerSystem customerSystem;
	private MockPricingSystem pricingSystem;
	private OrderController orderController;

	@Before
	public void setup() throws Exception
	{
		orderSystem = new MockOrderSystem();
		customerSystem = new MockCustomerSystem();
		pricingSystem = new MockPricingSystem();
		orderController = new OrderController(orderSystem, customerSystem, pricingSystem);

		pricingSystem.addSku("unpromoted", "unpromoted", "unpromoted");
		pricingSystem.addSku("promoted", "promoted", "promoted");
		pricingSystem.addPrice("unpromoted", UNPROMOTED_PRICE);
		pricingSystem.addPrice("promoted", UNPROMOTED_PRICE);
		pricingSystem.addPromotion("promoted", PROMOTED_PRICE_0);
		pricingSystem.addPromotion("promoted", PROMOTED_PRICE_1);
	}

	@Test
	public void testItemGetsDefaultPriceIfNoPromotion() throws Exception
	{
		Item item = orderController.createItem(mockProduct("unpromoted"), mockCustomer());
		assertNotNull(item);
		assertEquals(UNPROMOTED_PRICE, item.getPrice());
		assertNull(item.getPromotionId());
	}

	@Test
	public void testItemGetsLowestDiscountPriceIfPromotion() throws Exception
	{
		Item item = orderController.createItem(mockProduct("promoted"), mockCustomer());
		assertNotNull(item);
		assertEquals(PROMOTED_PRICE_0, item.getPrice());
		assertNotNull(item.getPromotionId());
	}

	@Test
	public void testCreateOrder() throws Exception
	{
		List<Item> items = new ArrayList<Item>();
		items.add(new ItemBuilder()
			.setProduct(mockProduct("promoted"))
			.setPrice(BigDecimal.valueOf(1.25))
			.build());
		Order order = orderController.createOrder(items, mockCustomer());
		assertNotNull(order);
	}

	@Test
	public void testExchangeItemGetsDefaultPriceIfNoPromotion() throws Exception
	{
		Item item = orderController.createExchangeItem(mockProduct("unpromoted"), mockOrder(), Collections.EMPTY_LIST);
		assertNotNull(item);
		assertEquals(UNPROMOTED_PRICE, item.getPrice());
		assertNull(item.getPromotionId());
	}

	@Test
	public void testExchangeItemGetsLowestDiscountPriceIfPromotion() throws Exception
	{
		Item item = orderController.createExchangeItem(mockProduct("promoted"), mockOrder(), Collections.EMPTY_LIST);
		assertNotNull(item);
		assertEquals(PROMOTED_PRICE_0, item.getPrice());
		assertNotNull(item.getPromotionId());
	}

	// !!! EXCHANGE POLICY !!!
	// Test that an item exchanged for another of the same kind incurs no extra charge.
	@Test
	public void testItemExchangedForAnotherOfSameKindAtNoExtraCharge() throws Exception
	{
		// Suppose that we sell a sweater that comes in more than one color...
		pricingSystem.addSku("123-blue", "123", "Blue Sweater");
		pricingSystem.addSku("123-green", "123", "Green Sweater");
		pricingSystem.addPrice("123", BigDecimal.valueOf(29.99));

		// Suppose that a promotion on the sweater ran, and that someone bought a blue one...
		pricingSystem.addPromotion("123", BigDecimal.valueOf(24.99));
		Item item = orderController.createItem(pricingSystem.getSku("123-blue"), mockCustomer());
		assertEquals(BigDecimal.valueOf(24.99), item.getPrice());
	}

	@Test
	public void testCreateExchangeOrder() throws Exception
	{
		List<Item> items = new ArrayList<Item>();
		items.add(new ItemBuilder()
			.setProduct(mockProduct("unpromoted"))
			.setPrice(BigDecimal.valueOf(1.25))
			.build());
		ExchangeOrder exchangeOrder =  orderController.createExchangeOrder(items, mockOrder(), mockOrder().getItems());
		assertNotNull(exchangeOrder);
	}

	private Order mockOrder()
	{
		List<Item> returnedItems = new ArrayList<Item>();
		returnedItems.add(new ItemBuilder()
			.setProduct(mockProduct("promoted"))
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

	private static Product mockProduct(String name)
	{
		return new ProductBuilder()
			.setId(name)
			.setSku(name)
			.setDescription(name)
			.build();
	}
}
