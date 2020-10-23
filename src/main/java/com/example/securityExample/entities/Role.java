package com.example.securityExample.entities;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum Role {
    ADMIN(Set.of(Permission.DEVELOPERS_WRITE, Permission.DEVELOPERS_READ)),
    USER(Set.of(Permission.DEVELOPERS_READ));

    private final Set<Permission> permissionSet;

    Role(Set<Permission> permissionSet) {
        this.permissionSet = permissionSet;
    }

    public Set<Permission> getPermissionSet() {
        return permissionSet;
    }

    public Set<SimpleGrantedAuthority> getAuthorities() {
        return permissionSet.stream().map(p ->
                new SimpleGrantedAuthority(p.getPermission())
        ).collect(Collectors.toSet());
    }
}
