package com.example.springboot.jwt.controller;

import java.util.Collection;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.jwt.entity.Organization;
import com.example.springboot.jwt.service.OrganizationService;

@RestController
@RequestMapping("organization")
public class OrganizationController {

	private final OrganizationService organizationService;

	public OrganizationController(OrganizationService organizationService) {
		this.organizationService = organizationService;
	}

	@GetMapping
	@PreAuthorize("@customRbacAuthorizationHandler.isGlobalAdminPrivilegeAssigned('ADMIN_ORGANIZATION_READ')")
	public Collection<Organization> getOrganizations() {
		return organizationService.getAllOrganizations();
	}

	@PostMapping
	@PreAuthorize("@customRbacAuthorizationHandler.isGlobalAdminPrivilegeAssigned('ADMIN_ORGANIZATION_WRITE')")
	public void addOrganization(@RequestBody Organization organization) {
		organizationService.addOrganization(organization);
	}

	@DeleteMapping("{id}")
	@PreAuthorize("@customRbacAuthorizationHandler.isGlobalAdminPrivilegeAssigned('ADMIN_ORGANIZATION_DELETE')")
	public void removeOrganization(@PathVariable long id) {
		organizationService.deleteOrganizationById(id);
	}
}
