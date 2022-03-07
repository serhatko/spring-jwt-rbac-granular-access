package com.example.springboot.jwt.security.rbac;

import java.util.ArrayList;
import java.util.List;

public class RoleLookup {

	public enum Roles {
		ADMIN_ROLE(new Role(0, "adminRole", getAdminRolePrivileges())),
		SOMECOMPANY_ORGANIZATION_ADMIN(
				new Role(1, "someCompanyOrganizationAdmin", getSomeCompanyOrganizationAdminPrivileges())),
		ACME_ORGANIZATION_ADMIN(new Role(2, "acmeOrganizationAdmin", getAcmeOrganizationAdminPrivileges())),
		SOMECOMPANY_WAREHOUSE_1_ADMIN(
				new Role(3, "someCompanyWarehouse1Admin", getSomeCompanyWarehouse1AdminPrivileges())),
		ACME_WAREHOUSE_1_ADMIN(new Role(4, "acmeWarehouse1Admin", getAcmeWarehouse1AdminPrivileges())),
		SOMECOMPANY_WAREHOUSE_1_PRODUCT_READER(
				new Role(5, "someCompanyWarehouse1ProductReader", getSomeCompanyWarehouse1ProductReaderPrivileges()));

		private final Role value;

		private Roles(Role role) {
			this.value = role;
		}

		public Role getValue() {
			return this.value;
		}
	}

	private static List<Privilege> getAdminRolePrivileges() {
		List<Privilege> adminRolePrivileges = new ArrayList<>();
		adminRolePrivileges.add(PrivilegeActionLookup.AdminActions.ADMIN.getValue());
		return adminRolePrivileges;
	}

	private static List<Privilege> getSomeCompanyOrganizationAdminPrivileges() {
		List<Privilege> someCompanyOrganizationAdminRolePrivileges = new ArrayList<>();
		someCompanyOrganizationAdminRolePrivileges.add(new Privilege(12,
				"0~" + PrivilegeActionLookup.OrganizationActions.ORGANIZATION_ADMIN.getValue().getName()));
		return someCompanyOrganizationAdminRolePrivileges;
	}

	private static List<Privilege> getAcmeOrganizationAdminPrivileges() {
		List<Privilege> acmeOrganizationAdminPrivileges = new ArrayList<>();
		acmeOrganizationAdminPrivileges.add(new Privilege(13,
				"1~" + PrivilegeActionLookup.OrganizationActions.ORGANIZATION_ADMIN.getValue().getName()));
		return acmeOrganizationAdminPrivileges;
	}

	private static List<Privilege> getSomeCompanyWarehouse1AdminPrivileges() {
		List<Privilege> productOrganizerRolePrivileges = new ArrayList<>();
		productOrganizerRolePrivileges.add(new Privilege(14,
				"0~" + "0~" + PrivilegeActionLookup.ResourceActions.RESOURCE_ADMIN.getValue().getName()));
		return productOrganizerRolePrivileges;
	}

	private static List<Privilege> getAcmeWarehouse1AdminPrivileges() {
		List<Privilege> productOrganizerRolePrivileges = new ArrayList<>();
		productOrganizerRolePrivileges.add(new Privilege(15,
				"1~" + "1~" + PrivilegeActionLookup.ResourceActions.RESOURCE_ADMIN.getValue().getName()));
		return productOrganizerRolePrivileges;
	}

	private static List<Privilege> getSomeCompanyWarehouse1ProductReaderPrivileges() {
		List<Privilege> productOrganizerRolePrivileges = new ArrayList<>();
		productOrganizerRolePrivileges.add(new Privilege(15,
				"0~" + "0~" + PrivilegeActionLookup.ResourceActions.RESOURCE_ADMIN.getValue().getName()));
		return productOrganizerRolePrivileges;
	}

}