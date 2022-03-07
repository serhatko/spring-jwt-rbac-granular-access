package com.example.springboot.jwt.entity;

public class Organization {
	private long id;
	private String name;
	private boolean canBeAdmin;
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
	public Organization(long id, String name, boolean canBeAdmin) {
		this.id = id;
		this.name = name;
		this.canBeAdmin=canBeAdmin;
	}
	public Organization() {
	}
	public boolean isCanBeAdmin() {
		return canBeAdmin;
	}
	public void setCanBeAdmin(boolean canBeAdmin) {
		this.canBeAdmin = canBeAdmin;
	}
	
}
