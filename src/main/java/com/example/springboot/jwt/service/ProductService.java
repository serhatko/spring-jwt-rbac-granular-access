package com.example.springboot.jwt.service;

import com.example.springboot.jwt.context.StaticContext;
import com.example.springboot.jwt.entity.Product;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class ProductService {

	private final Map<Long, Product> productMap = StaticContext.getProducts();
	private final AtomicInteger idCounter = new AtomicInteger(2);

	public ProductService() {
		addProduct(new Product("First Product", "First Product Description from someCompany",
				StaticContext.staticResourceOfSomeCompany));
		addProduct(new Product("Second Product", "Second Product Description from Acme",
				StaticContext.staticResourceOfAcme));
	}

	public Collection<Product> getAllProducts(String organizationId, String resourceId) {
		return productMap.values().stream()
				.filter(x -> x.getResource().getId() == Long.parseLong(resourceId)
						&& x.getResource().getOwnerOrganization().getId() == Long.parseLong(organizationId))
				.collect(Collectors.toList());
	}

	public Product getProductById(long id) {
		return productMap.get(id);
	}

	public void addProduct(Product product) {
		if (productMap.values().stream().anyMatch(p -> p.getName().equals(product.getName()))) {
			throw new IllegalArgumentException(String.format("Product with name %s already exists", product.getName()));
		}

		product.setId(idCounter.incrementAndGet());
		productMap.put(product.getId(), product);
	}

	public void deleteProductById(long id) {
		if (!productMap.containsKey(id)) {
			throw new IllegalArgumentException(String.format("Product with id %d doesn't exist", id));
		}

		productMap.remove(id);
	}

}
