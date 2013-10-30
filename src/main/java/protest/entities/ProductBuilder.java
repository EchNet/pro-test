package protest.entities;

// Builder for class Product.
public class ProductBuilder implements Builder<Product>
{
	private String id;
	private String sku;
	private String description;

	public ProductBuilder setId(String id)
	{
		this.id = id;
		return this;
	}

	public ProductBuilder setSku(String sku)
	{
		this.sku = sku;
		return this;
	}

	public ProductBuilder setDescription(String description)
	{
		this.description = description;
		return this;
	}

	@Override
	public Product build()
	{
		if (id == null || sku == null || description == null) {
			throw new IllegalStateException();
		}
		Product product = new Product();
		product.id = id;
		product.sku = sku;
		product.description = description;
		return product;
	}
}
