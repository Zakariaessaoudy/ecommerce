package fst.ecommerce.security.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

/**
 * JwtAuthenticationFilter
 * ------------------------
 * Intercepts incoming requests to:
 * - Extract JWT from Authorization header
 * - Validate token
 * - Set Authentication in SecurityContext
 */
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        // TODO: implement JWT validation logic
        filterChain.doFilter(request, response);
    }
}
