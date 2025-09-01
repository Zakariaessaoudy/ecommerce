package fst.ecommerce.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

/**
 * MethodSecurityConfig
 * ---------------------
 * Enables method-level security using:
 * - @PreAuthorize
 * - @Secured
 * - @RolesAllowed
 */
@Configuration
@EnableMethodSecurity
public class MethodSecurityConfig {
}
