package com.example.springboot.jwt.security.rbac;

import java.util.Arrays;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.example.springboot.jwt.context.StaticContext;
import com.example.springboot.jwt.entity.Organization;
import com.example.springboot.jwt.entity.Resource;
import com.example.springboot.jwt.security.rbac.RoleLookup.Roles;

@Component("customRbacAuthorizationHandler")
public class CustomRbacAuthorizationHandler {

	public boolean isGlobalAdminPrivilegeAssigned(String privilege) {
		if (SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
			for (GrantedAuthority g : SecurityContextHolder.getContext().getAuthentication().getAuthorities()) {
				for (Roles r : RoleLookup.Roles.values()) {
					if (r.getValue().toString().equals(g.getAuthority())) {
						return (r.getValue().getPrivileges().stream().anyMatch(x -> x.getName().equals(privilege))
								|| r.getValue().getPrivileges().stream()
										.anyMatch(x -> x.getName()
												.equals(PrivilegeActionLookup.AdminActions.ADMIN.getValue().getName())))
								&& checkOrganizationCanBeAdmin(
										SecurityContextHolder.getContext().getAuthentication().getName().split("@")[1]);
					}
				}
			}
		}
		return false;
	}

	private boolean checkOrganizationCanBeAdmin(String organization) {
		return StaticContext.getOrganizations().stream().filter(x -> x.getName().equals(organization.replace(".", "_")))
				.anyMatch(x -> x.isCanBeAdmin());
	}

	public boolean isOrganizationBasedPrivilegeAssigned(String privilege, String organizationId) {
		if (SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
			for (GrantedAuthority g : SecurityContextHolder.getContext().getAuthentication().getAuthorities()) {
				for (Roles r : RoleLookup.Roles.values()) {
					if (((r.getValue().getPrivileges().stream().anyMatch(x -> x.getName().equals(privilege))
							|| r.getValue().getPrivileges().stream()
									.anyMatch(x -> x.getName()
											.equals(PrivilegeActionLookup.AdminActions.ADMIN.getValue().getName())))
							&& checkOrganizationCanBeAdmin(
									SecurityContextHolder.getContext().getAuthentication().getName().split("@")[1]))
							|| (r.getValue().toString().equals(g.getAuthority())
									&& (r.getValue().getPrivileges().stream()
											.anyMatch(x -> x.getName().equals(organizationId + "~"
													+ PrivilegeActionLookup.OrganizationActions.ORGANIZATION_ADMIN
															.getValue().getName()))
											|| r.getValue().getPrivileges().stream().anyMatch(
													x -> x.getName().equals(organizationId + "~" + privilege))))) {
						return true;
					}
				}
			}
		}
		return false;
	}

	private Resource getResourceById(long id) {
		return StaticContext.getResources().stream().filter(x -> x.getId() == id).findFirst()
				.orElse(new Resource(-1, "", new Organization(-1, "", false)));
	}

	public boolean isResourceBasedPrivilegeAssigned(String privilege, String organizationId, String resourceId) {
		try {
			System.out.println("priv:" + privilege + ", orgId:" + organizationId + ", resId:" + resourceId);
			if (SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
				System.out.println();
				for (GrantedAuthority g : SecurityContextHolder.getContext().getAuthentication().getAuthorities()) {
					for (Roles r : RoleLookup.Roles.values()) {
						if (((r.getValue().getPrivileges().stream().anyMatch(x -> x.getName().equals(privilege))
								|| r.getValue().getPrivileges().stream()
										.anyMatch(x -> x.getName()
												.equals(PrivilegeActionLookup.AdminActions.ADMIN.getValue().getName())))
								&& checkOrganizationCanBeAdmin(
										SecurityContextHolder.getContext().getAuthentication().getName().split("@")[1]))
								|| (r.getValue().toString().equals(g.getAuthority())
										&& (r.getValue().getPrivileges().stream()
												.anyMatch(x -> x.getName().equals(organizationId + "~"
														+ PrivilegeActionLookup.OrganizationActions.ORGANIZATION_ADMIN
																.getValue().getName()))
												|| r.getValue().getPrivileges().stream().anyMatch(x -> x.getName()
														.equals(organizationId + "~" + resourceId + "~"
																+ PrivilegeActionLookup.ResourceActions.RESOURCE_ADMIN
																		.getValue().getName()))
												|| r.getValue().getPrivileges().stream()
														.anyMatch(x -> x.getName().equals(
																organizationId + "~" + resourceId + "~" + privilege)))
										&& organizationId
												.equals(Long.toString(getResourceById(Long.parseLong(resourceId))
														.getOwnerOrganization().getId())))) {
							return true;
						}
					}
				}
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}