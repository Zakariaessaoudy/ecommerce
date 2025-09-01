package fst.ecommerce.security.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * UserDetailsServiceImpl
 * -----------------------
 * Loads user-specific data from the database.
 * Used by Spring Security during authentication.
 */
public class UserDetailsServiceImpl implements UserDetailsService {
    // TODO: implement loadUserByUsername
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
