package fst.ecommerce.security.util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.web.access.AccessDeniedHandler;
import java.io.IOException;

/**
 * CustomAccessDeniedHandler
 * --------------------------
 * Handles 403 Forbidden responses when a user lacks permissions.
 */
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       org.springframework.security.access.AccessDeniedException accessDeniedException)
            throws IOException {
        response.sendError(HttpServletResponse.SC_FORBIDDEN, "Forbidden");
    }
}
