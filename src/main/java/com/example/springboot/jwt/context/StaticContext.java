package com.example.springboot.jwt.context;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.example.springboot.jwt.entity.Organization;
import com.example.springboot.jwt.entity.Product;
import com.example.springboot.jwt.entity.Resource;
import com.example.springboot.jwt.service.ProductService;

public class StaticContext {

	private static List<Organization> organizations = new ArrayList<>();
	private static List<Resource> resources = new ArrayList<>();
	private static Map<Long, Product> products = new ConcurrentHashMap<>();

	public static final Organization someCompany = new Organization(0, "somecompany.com".replace(".", "_"), false);
	public static final Resource staticResourceOfSomeCompany = new Resource(0, "Warehouse#1", someCompany);

	public static final Organization acme = new Organization(1, "acme.com".replace(".", "_"), true);
	public static final Resource staticResourceOfAcme = new Resource(1, "AcmeWarehouse#1", acme);

	public static List<Organization> getOrganizations() {
		return organizations;
	}

	public static void setOrganizations(List<Organization> organizations) {
		StaticContext.organizations = organizations;
	}

	public static void initializeContext() {
		organizations.add(someCompany);
		resources.add(staticResourceOfSomeCompany);
		organizations.add(acme);
		resources.add(staticResourceOfAcme);
	}

	public static List<Resource> getResources() {
		return resources;
	}

	public static Map<Long, Product> getProducts() {
		return products;
	}

	public static void setProducts(Map<Long, Product> products) {
		StaticContext.products = products;
	}

	public static void setResources(List<Resource> resources) {
		StaticContext.resources = resources;
	}

}
