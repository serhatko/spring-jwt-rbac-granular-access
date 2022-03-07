package com.example.springboot.jwt.security.rbac;

import org.springframework.stereotype.Component;

@Component("privilegeLookup")
public class PrivilegeActionLookup {

	public enum ResourceActions {
		RESOURCE_ADMIN(new Privilege(0, "RESOURCE_ADMIN")), PRODUCTS_READ(new Privilege(1, "PRODUCTS_READ")),
		RODUCTS_WRITE(new Privilege(2, "PRODUCTS_WRITE")), PRODUCTS_DELETE(new Privilege(3, "PRODUCTS_DELETE"));

		private final Privilege value;

		private ResourceActions(Privilege privilege) {
			this.value = privilege;
		}

		public Privilege getValue() {
			return this.value;
		}
	}

	public enum OrganizationActions {
		ORGANIZATION_ADMIN(new Privilege(4, "ORGANIZATION_ADMIN")),
		ORGANIZATION_RESOURCE_READ(new Privilege(5, "ORGANIZATION_RESOURCE_READ")),
		ORGANIZATION_RESOURCE_WRITE(new Privilege(6, "ORGANIZATION_RESOURCE_WRITE")),
		ORGANIZATION_RESOURCE_DELETE(new Privilege(7, "ORGANIZATION_RESOURCE_DELETE"));

		private final Privilege value;

		private OrganizationActions(Privilege privilege) {
			this.value = privilege;
		}

		public Privilege getValue() {
			return this.value;
		}
	}

	public enum AdminActions {
		ADMIN(new Privilege(8, "ADMIN")), ADMIN_ORGANIZATION_READ(new Privilege(9, "ADMIN_ORGANIZATION_READ")),
		ADMIN_ORGANIZATION_WRITE(new Privilege(10, "ADMIN_ORGANIZATION_WRITE")),
		ADMIN_ORGANIZATION_DELETE(new Privilege(11, "ADMIN_ORGANIZATION_DELETE"));

		private final Privilege value;

		private AdminActions(Privilege privilege) {
			this.value = privilege;
		}

		public Privilege getValue() {
			return this.value;
		}
	}

}
