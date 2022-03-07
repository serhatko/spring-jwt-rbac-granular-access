package com.example.springboot.jwt.service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;

import com.example.springboot.jwt.context.StaticContext;
import com.example.springboot.jwt.entity.Organization;

@Service
public class OrganizationService {

	private final List<Organization> orgList = StaticContext.getOrganizations();
	private final AtomicInteger idCounter = new AtomicInteger(2);

	public OrganizationService() {
		System.out.println("OrganizationServiceConstructor");
		addOrganization(new Organization(0, "somecompany.com", false));
		addOrganization(new Organization(1, "acme.com", true));
	}

	public List<Organization> getAllOrganizations() {
		return orgList;
	}

	public Optional<Organization> getOrganizationById(long id) {
		return orgList.stream().findAny().filter(x -> x.getId() == id);
	}

	public void addOrganization(Organization org) {
		if (orgList.stream().anyMatch(p -> p.getName().equals(org.getName()))) {
			throw new IllegalArgumentException(
					String.format("Organization with name %s already exists", org.getName()));
		}

		org.setId(idCounter.incrementAndGet());
		org.setName(org.getName().replace(".", "_"));
		orgList.add(org);
	}

	public void deleteOrganizationById(long id) {
		if (orgList.stream().noneMatch(x -> x.getId() == id)) {
			throw new IllegalArgumentException(String.format("Organization with id %d doesn't exist", id));
		}

		orgList.removeIf(x -> x.getId() == id);
	}

}
