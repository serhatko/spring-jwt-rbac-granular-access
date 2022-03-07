package com.example.springboot.jwt.entity;

public class Product {

	private long id;
	private String name;
	private String description;
	private Resource resource;

	public Product() {
	}

	public Product(String name, String description, Resource resource) {
		this.name = name;
		this.description = description;
		this.resource = resource;
	}

	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}
