package fst.ecommerce.security.model;

import lombok.Data;

/**
 * AuthRequest
 * ------------
 * DTO for login requests.
 * Contains username and password.
 */
@Data
public class AuthRequest {
    private String username;
    private String password;

}
