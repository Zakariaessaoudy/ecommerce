
# Modern Security Layer with Spring Security + JWT

**Onboarding Guide for Developers**

This document explains, step by step, how we implement a **production-grade security layer** in our Spring Boot e-commerce application using the **modern Spring Security approach** (without `WebSecurityConfigurerAdapter`).

---

## 1) High-Level Architecture

**Folders & Responsibilities:**

* `config/`

    * **SecurityConfig** — Defines `SecurityFilterChain`, session policy, filter ordering, URL access rules, exposes `AuthenticationManager`.
    * **MethodSecurityConfig** — Enables method-level security (`@PreAuthorize` / `@PostAuthorize`).
    * **NoSecurityConfig** — Optional config for development (H2 console, Swagger) or disabling security in test profiles.

* `jwt/`

    * **JwtTokenProvider** — Create/validate/parse JWT tokens (claims, expiry, signature).
    * **JwtAuthenticationFilter** — A `OncePerRequestFilter` that extracts the token, validates it, and loads authentication into `SecurityContext`.
    * **JwtAuthenticationEntryPoint** — Handles unauthorized access (401).

* `model/`

    * **AppUser, Role** — Entities representing users and roles.
    * **AuthRequest, AuthResponse** — DTOs for login request/response.

* `service/`

    * **AuthService** — Authentication logic, login, token generation (and refresh).
    * **PasswordEncoderConfig** — Provides `PasswordEncoder` bean (BCrypt).
    * **UserDetailsServiceImpl** — Loads user for authentication.

* `util/`

    * **CustomAccessDeniedHandler** — Handles forbidden access (403).
    * **SecurityUtils** — Helper methods (get current user, check roles, etc.).

---



# Step 2 — Recommended Implementation Order (Detailed)

## **1. PasswordEncoderConfig (BCrypt password encoder)**

* **What**: Create a config class that exposes a `PasswordEncoder` bean.
* **Why**: Needed by authentication and user registration logic. Spring Security requires a password encoder to check user passwords securely.
* **Best practice**: Use `BCryptPasswordEncoder` with a strength of 10–12.

👉 Without this, your authentication flow cannot compare user passwords against DB values.

---

## **2. Model Entities (AppUser, Role) + repositories**

* **What**:

    * `AppUser`: represents the system’s users. Fields: `id`, `username`, `email`, `password`, `Set<Role> roles`, `enabled`, etc.
    * `Role`: represents user roles (e.g., `ADMIN`, `CUSTOMER`).
    * Repositories: `UserRepository`, `RoleRepository` extending `JpaRepository`.
* **Why**: The core domain for authentication. JWTs are generated based on user identity and roles.
* **Best practice**:

    * Store only hashed passwords.
    * Normalize role names (`ADMIN` not `ROLE_ADMIN`; prefix added at mapping).

👉 This step ensures we have a **source of truth** for user authentication.

---

## **3. UserDetailsServiceImpl (map AppUser → UserDetails)**

* **What**: Implement `UserDetailsService` with `loadUserByUsername`.
* **Why**: Spring Security uses this to fetch user details during authentication.
* **How**: Query DB for `AppUser`, map to `UserDetails` (`username`, `password`, `authorities`).

👉 Now Spring Security can authenticate real users from your DB.

---

## **4. JwtTokenProvider (generate, validate, parse JWTs)**

* **What**:

    * `generateToken(Authentication auth)` → create JWT with claims (user, roles, expiry).
    * `validateToken(token)` → check signature + expiry.
    * `getAuthentication(token)` → return an `Authentication` with authorities.
* **Why**: JWT is the **core mechanism** for stateless authentication.
* **Best practice**:

    * Include `sub` (username), `roles`, `iat`, `exp`.
    * Use strong keys (HMAC ≥ 256 bits, or RSA).

👉 At this stage, you can **generate and parse JWTs**, even before integrating into filters.

---

## **5. JwtAuthenticationEntryPoint + CustomAccessDeniedHandler**

* **What**:

    * `JwtAuthenticationEntryPoint` → returns **401 Unauthorized** JSON when user is not logged in or token is invalid.
    * `CustomAccessDeniedHandler` → returns **403 Forbidden** JSON when user is authenticated but lacks permission.
* **Why**: By default, Spring returns HTML error pages. In APIs, we need clean JSON responses.
* **Best practice**: Always include timestamp, status code, message, and path in error JSON.

👉 This ensures consistent error handling once you start protecting endpoints.

---

## **6. JwtAuthenticationFilter (per-request token validation)**

* **What**:

    * A `OncePerRequestFilter` that:

        * Extracts token from `Authorization: Bearer <token>`.
        * Validates it with `JwtTokenProvider`.
        * Loads authentication into `SecurityContext`.
* **Why**: This is where every request gets authenticated statelessly.
* **Best practice**: Clear `SecurityContext` after request completes to avoid thread leaks.

👉 After this, your app can protect endpoints with JWT.

---

## **7. AuthService (+ Controller)**

* **What**:

    * `AuthService.login(AuthRequest)` → authenticates using `AuthenticationManager`, generates token with `JwtTokenProvider`.
    * `AuthController` → exposes `/api/auth/login`.
* **Why**: This is the **entry point for clients** to obtain JWTs.
* **Best practice**:

    * Return both `accessToken` and `refreshToken`.
    * Add expiry time and roles in response.

👉 Now clients can log in and start using JWTs for protected endpoints.

---

## **8. SecurityConfig (SecurityFilterChain)**

* **What**:

    * Configure `HttpSecurity`: disable CSRF, stateless sessions, register `JwtAuthenticationFilter`.
    * Define URL access rules (`/api/auth/**` permitAll, everything else authenticated).
* **Why**: This ties all the above pieces into Spring’s security chain.
* **Best practice**:

    * Always enforce `SessionCreationPolicy.STATELESS`.
    * Allow Swagger, H2-console only in dev.

👉 At this stage, the whole JWT-based security layer is operational.

---

## **9. MethodSecurityConfig**

* **What**: Enable `@PreAuthorize`, `@PostAuthorize`, `@Secured`.
* **Why**: Sometimes URL-based rules are not enough. Business logic often requires role checks inside service methods.
* **Best practice**: Prefer `@PreAuthorize("hasRole('ADMIN')")` for clarity.

👉 Now you can enforce both **endpoint-level** and **method-level** security.

---

## **10. NoSecurityConfig**

* **What**: A security config activated only in `dev` profile.
* **Why**: Allow H2-console, Swagger, or other dev tools without JWT in local testing.
* **Best practice**: Keep this disabled in production profiles.

👉 This helps developers test quickly without fighting JWT in local runs.

---

## **11. Tests (unit + integration)**

* **Unit tests**:

    * `JwtTokenProviderTest`: check generate/validate/parse logic.
    * `UserDetailsServiceImplTest`: check mapping from DB user to `UserDetails`.
* **Integration tests**:

    * Use `MockMvc` to call `/api/auth/login`, store token, and access a protected endpoint.
    * Verify 401 (no token), 403 (wrong role), and 200 (correct role).
* **Best practice**: Automate JWT flows in CI/CD pipelines.

👉 This ensures your security layer works and prevents regressions.

---

# 📌 Why This Order?

* You start with **password encoder and entities** (the foundation).
* Then **UserDetailsService** so Spring Security can authenticate against DB.
* Next **JWT provider** to handle tokens.
* Then **error handlers + filter** so the request pipeline can process tokens.
* Only after that you add **AuthService/Controller** for login and **SecurityConfig** to tie everything.
* Finally, **method-level security, dev profile, and tests** round it out.

---


## 3) Request Flow (Login → Token → Protected Endpoints)

1. **Client** sends `POST /api/auth/login` with `AuthRequest {username, password}`.
2. **AuthController** calls `AuthService.login(...)`.
3. **AuthService**:

    * Creates `UsernamePasswordAuthenticationToken`.
    * Uses `AuthenticationManager` → triggers `UserDetailsServiceImpl`.
    * On success, generates `JWT access token` (and refresh token if needed).
4. **Response**: `AuthResponse {accessToken, refreshToken, expiry, roles}`.
5. **Client** stores access token (e.g. memory/localStorage/secure storage).
6. **Subsequent requests** include header: `Authorization: Bearer <token>`.
7. **JwtAuthenticationFilter** (inside `SecurityFilterChain`):

    * Extracts and validates token.
    * Creates `Authentication` object → set into `SecurityContextHolder`.
8. **Controller/Service** layers access current user via `@AuthenticationPrincipal` or `SecurityContext`.
9. **If missing/expired token** → `AuthenticationEntryPoint` returns 401.
   **If insufficient role** → `AccessDeniedHandler` returns 403.

---

## 4) Role & Authority Management (Best Practices)

* **Modeling**: Store roles as `USER`, `ADMIN` in DB, and prepend with `ROLE_` when converting to authorities.
* **Checks**:

    * HTTP level: `.requestMatchers("/admin/**").hasRole("ADMIN")`.
    * Method level: `@PreAuthorize("hasRole('ADMIN')")`.
* **Fine-grained permissions**: Consider authorities like `ORDER_READ`, `PRODUCT_WRITE` for enterprise-level systems.

---

## 5) SecurityContext Management

* `JwtAuthenticationFilter` sets authentication into `SecurityContextHolder`.
* Always **clear context** after the filter chain if using thread pools:

  ```java
  finally { SecurityContextHolder.clearContext(); }
  ```
* Access authenticated user via:

    * `@AuthenticationPrincipal` in controllers, or
    * `SecurityUtils.getCurrentUser()` in services.

---

## 6) Error Handling (401 / 403)

**Professional JSON responses** instead of HTML errors:

```json
{
  "timestamp": "2025-09-20T09:12:34Z",
  "status": 401,
  "error": "Unauthorized",
  "message": "Invalid or expired token",
  "path": "/api/orders"
}
```

* **401 Unauthorized** → `JwtAuthenticationEntryPoint`.
* **403 Forbidden** → `CustomAccessDeniedHandler`.

---

## 7) Production-Ready Considerations

* Use **RSA keys (RS256)** or strong HMAC secrets (≥256 bits).
* **Access tokens** short-lived (10–15min), **Refresh tokens** longer (7–30 days).
* Store refresh tokens securely (DB hashed or HttpOnly cookie).
* Implement **refresh token rotation** to prevent replay attacks.
* Consider **token blacklist** (Redis) for logout/revocation.
* Enable **method-level logging** for login, logout, failures.
* Use `BCryptPasswordEncoder` with strength ≥ 12.
* Store secrets in environment variables or Vault, never in code.

---

## 8) Testing Strategy

* **Unit tests** → `JwtTokenProvider`, `UserDetailsServiceImpl`.
* **Integration tests** → MockMvc/WebTestClient verifying login, protected endpoints, 401/403 responses.
* **End-to-End** → Full login → token use → refresh flow.

---

## 9) Developer Checklist

1. Review `PasswordEncoderConfig`.
2. Verify `UserDetailsServiceImpl` mapping logic.
3. Implement/test `JwtTokenProvider`.
4. Add `JwtAuthenticationFilter` into the chain.
5. Configure `SecurityConfig` (whitelist `/api/auth/**`, swagger, static).
6. Build `AuthService` & `AuthController`.
7. Enable `MethodSecurityConfig`.
8. Add tests.
9. Review secrets, token expiry, refresh handling.

---

## 10) Example Code Snippets

**PasswordEncoderConfig**

```java
@Configuration
public class PasswordEncoderConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }
}
```

**JwtAuthenticationFilter**

```java
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtTokenProvider jwtTokenProvider;

    public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws ServletException, IOException {
        try {
            String header = req.getHeader(HttpHeaders.AUTHORIZATION);
            if (header != null && header.startsWith("Bearer ")) {
                String token = header.substring(7);
                if (jwtTokenProvider.validateToken(token)) {
                    Authentication auth = jwtTokenProvider.getAuthentication(token);
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            }
            chain.doFilter(req, res);
        } finally {
            SecurityContextHolder.clearContext();
        }
    }
}
```

**SecurityConfig**

```java
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final JwtAuthenticationFilter jwtFilter;
    private final JwtAuthenticationEntryPoint entryPoint;
    private final CustomAccessDeniedHandler accessDeniedHandler;

    public SecurityConfig(JwtAuthenticationFilter jwtFilter,
                          JwtAuthenticationEntryPoint entryPoint,
                          CustomAccessDeniedHandler accessDeniedHandler) {
        this.jwtFilter = jwtFilter;
        this.entryPoint = entryPoint;
        this.accessDeniedHandler = accessDeniedHandler;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .exceptionHandling()
              .authenticationEntryPoint(entryPoint)
              .accessDeniedHandler(accessDeniedHandler)
            .and()
            .authorizeHttpRequests()
              .requestMatchers("/api/auth/**", "/v3/api-docs/**", "/swagger-ui/**").permitAll()
              .anyRequest().authenticated()
            .and()
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
```

---

✅ With this structure, your new developers can onboard quickly and understand how login, JWT tokens, authorization, and role checks fit together in a **professional-grade security layer**.

---

Would you like me to **generate the full ready-to-use code for each file in your `security/` package** (config, jwt, service, util, etc.) so you can drop it directly into your project?
