package fst.ecommerce.security.model;

import lombok.RequiredArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * AuthResponse
 * -------------
 * DTO for login responses.
 * Contains JWT token and user details.
 */

public class AuthResponse {
    private String accessToken;
    private String refreshToken;
    private Date expiresAt;
    private String username;
    private List<String> roles;


    public AuthResponse(String accessToken, String refreshToken, Date expirationTime, String name, List<String> collect) {
    }
}
