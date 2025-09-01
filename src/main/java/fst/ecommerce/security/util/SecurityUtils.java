package fst.ecommerce.security.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * SecurityUtils
 * --------------
 * Utility class for:
 * - Fetching current authenticated user
 * - Checking roles
 */
public class SecurityUtils {
    public static Authentication getCurrentUser() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
