package com.example.springboot.jwt.controller;

import com.example.springboot.jwt.entity.Product;
import com.example.springboot.jwt.service.ProductService;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/products")
public class ProductController {

	private final ProductService productService;

	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@PreAuthorize("@customRbacAuthorizationHandler.isResourceBasedPrivilegeAssigned('PRODUCTS_READ',#organization,#resource)")
	@GetMapping("/{organization}/{resource}/getProducts")
	public Collection<Product> getProducts(@PathVariable("organization") String organizationId,
			@PathVariable("resource") String resourceId) {
		return productService.getAllProducts(organizationId, resourceId);
	}

	@PreAuthorize("@customRbacAuthorizationHandler.isResourceBasedPrivilegeAssigned('PRODUCTS_WRITE',#organization,#resource)")
	@PostMapping("/{organization}/{resource}/addProduct")
	public void addProduct(@RequestBody Product product) {
		productService.addProduct(product);
	}

	@PreAuthorize("@customRbacAuthorizationHandler.isResourceBasedPrivilegeAssigned('PRODUCTS_DELETE',#organization,#resource)")
	@DeleteMapping("/{organization}/{resource}/removeProduct/{id}")
	public void removeProduct(@PathVariable long id) {
		productService.deleteProductById(id);
	}
}
