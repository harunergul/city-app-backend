package com.aaron.iluslinn.config;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.springframework.security.access.prepost.PreAuthorize;

@Retention(RetentionPolicy.RUNTIME)
@PreAuthorize("hasAuthority('ROLE_ALLOW_EDIT')")
public @interface  RoleAllowEditOnly {
}
