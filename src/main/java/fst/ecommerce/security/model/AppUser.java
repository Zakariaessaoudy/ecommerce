package fst.ecommerce.security.model;

import jakarta.persistence.*;
import java.util.Set;

/**
 * AppUser
 * --------
 * Represents a user in the system:
 * - Stores username, password, and roles
 */
@Entity
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> roles;
}
