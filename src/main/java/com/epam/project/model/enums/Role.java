package com.epam.project.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.epam.project.model.enums.Permission.*;

@RequiredArgsConstructor
public enum Role {
    USER(Collections.emptySet()),

    VENDOR(
            Set.of(
                    VENDOR_READ,
                    VENDOR_CREATE,
                    VENDOR_DELETE,
                    VENDOR_UPDATE
            )
    ),
    BUYER(
            Set.of(
                    BUYER_UPDATE,
                    BUYER_CREATE,
                    BUYER_DELETE,
                    BUYER_READ
            )
    );


    @Getter
    private final Set<Permission> permissions;

    public List<SimpleGrantedAuthority> getAuthorities() {
        var authorities = getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}
