package com.epam.project.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {
    VENDOR_READ("vendor:read"),
    VENDOR_UPDATE("vendor:update"),
    VENDOR_CREATE("vendor:create"),
    VENDOR_DELETE("vendor:delete"),
    BUYER_READ("buyer:read"),
    BUYER_UPDATE("buyer:update"),
    BUYER_CREATE("buyer:create"),
    BUYER_DELETE("buyer:delete");

    @Getter
    private final String permission;
}
