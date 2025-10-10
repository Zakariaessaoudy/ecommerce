package fst.ecommerce.security.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.stream.Collectors;

/**
 * JwtTokenProvider
 * -----------------
 * Utility class for generating, validating, and parsing JWT tokens.
 * - Generates tokens when user logs in
 * - Validates tokens on incoming requests
 * - Extracts username/roles from tokens to rebuild Authentication object
 */
@Component
public class JwtTokenProvider {

    /**
     * Secret key for signing JWTs.
     * - Must be at least 256 bits for HS256.
     * - Best practice: load from application.properties or environment variables.
     */
    private final Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    /**
     * Token validity duration (example: 1 hour = 3600000 ms).
     * After this time, the token will expire and be rejected.
     */
    private final long validityInMs = 3600000; // 1 hour

    // ----------------------------------------------------------------------

    /**
     * Generate a JWT token for the authenticated user.
     * @param authentication Spring Security Authentication object
     * @return signed JWT as String
     */
    public String generateToken(Authentication authentication) {
        // Extract username (the subject of the token)
        String username = authentication.getName();

        // Extract user roles/authorities (e.g., ROLE_USER, ROLE_ADMIN)
        String roles = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(",")); // join as comma-separated string

        // Define issue time and expiry
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + validityInMs);

        // Build the token
        return Jwts.builder()
                .setSubject(username)              // "sub" claim = username
                .claim("roles", roles)             // custom claim = user roles
                .setIssuedAt(now)                  // "iat" = issued at
                .setExpiration(expiryDate)         // "exp" = expiration date
                .signWith(secretKey, SignatureAlgorithm.HS256) // sign with key
                .compact();                        // generate token string
    }
    public String generateAccessToken(Authentication authentication) {
        String username = authentication.getName();
        String roles = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + validityInMs);

        return Jwts.builder()
                .setSubject(username)
                .claim("roles", roles)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateRefreshToken(Authentication authentication) {
        String username = authentication.getName();

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + validityInMs);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }
    // ----------------------------------------------------------------------

    /**
     * Validate a JWT token.
     * @param token JWT string
     * @return true if valid, false otherwise
     */
    public boolean validateToken(String token) {
        try {
            // Parse the token (verifies signature and expiration)
            Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token);
            return true; // no exception = valid
        } catch (ExpiredJwtException e) {
            System.out.println("Token expired: " + e.getMessage());
        } catch (JwtException | IllegalArgumentException e) {
            System.out.println("Invalid token: " + e.getMessage());
        }
        return false;
    }

    // ----------------------------------------------------------------------

    /**
     * Extract Authentication object (username + roles) from a valid JWT.
     * This allows Spring Security to recognize the user from the token.
     *
     * @param token JWT string
     * @param userDetails already loaded user from DB (via UserDetailsServiceImpl)
     * @return Authentication object (to inject into SecurityContext)
     */
    public Authentication getAuthentication(String token, UserDetails userDetails) {
        // Parse claims (payload of the JWT)
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();

        // Extract roles as array
        var authorities = claims.get("roles", String.class).split(",");

        // Create Authentication with username + authorities
        return new UsernamePasswordAuthenticationToken(
                userDetails,    // principal
                "",             // no credentials needed here
                java.util.Arrays.stream(authorities)
                        .map(org.springframework.security.core.authority.SimpleGrantedAuthority::new)
                        .toList()
        );
    }

    // ----------------------------------------------------------------------

    /**
     * Extract username (subject) directly from token.
     * Useful when you only need to know "who" is logged in.
     *
     * @param token JWT string
     * @return username (subject of the token)
     */
    public String getUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
    private Claims parseClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    public Date getExpirationTime(String token) {
        return parseClaims(token).getExpiration();
    }
}
