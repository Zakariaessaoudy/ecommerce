package fst.ecommerce.security.jwt;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.web.AuthenticationEntryPoint;
import java.io.IOException;

/**
 * JwtAuthenticationEntryPoint
 * ----------------------------
 * Handles unauthorized access attempts:
 * - Sends HTTP 401 Unauthorized response
 */
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         org.springframework.security.core.AuthenticationException authException)
            throws IOException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
    }
}
