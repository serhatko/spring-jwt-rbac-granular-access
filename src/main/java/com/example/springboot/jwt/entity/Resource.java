package com.example.springboot.jwt.entity;

public class Resource {
	private long id;
	private String name;
	private Organization ownerOrganization;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Organization getOwnerOrganization() {
		return ownerOrganization;
	}

	public void setOwnerOrganization(Organization ownerOrganization) {
		this.ownerOrganization = ownerOrganization;
	}

	public Resource(long id, String name, Organization ownerOrganization) {
		this.id = id;
		this.name = name;
		this.ownerOrganization = ownerOrganization;
	}

}
