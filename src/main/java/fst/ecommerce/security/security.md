# 🔐 Security Module (Spring Boot + JWT)

This folder contains all the code that makes our application secure:
- It manages **login** (authentication).
- It protects **endpoints** based on user roles (authorization).
- It uses **JWT tokens** so that users don’t need to log in on every request.

---

## 📂 Folder Structure

### 1. `config/`
Contains the main **security settings**.

- **SecurityConfig.java** → Defines which endpoints are public or protected, and how login works.
- **MethodSecurityConfig.java** → Allows us to use annotations like `@PreAuthorize("hasRole('ADMIN')")` on methods.

---

### 2. `jwt/`
Handles everything related to **JWT (JSON Web Tokens)**.

- **JwtAuthenticationEntryPoint.java** → Sends a **401 Unauthorized** response when someone tries to access without logging in.
- **JwtAuthenticationFilter.java** → Reads the token from requests, checks if it’s valid, and sets the logged-in user.
- **JwtTokenProvider.java** → Creates tokens when a user logs in, and validates them on requests.

---

### 3. `model/`
Holds the **data objects** used during login and security.

- **AppUser.java** → Represents a user (username, password, roles).
- **Role.java** → Represents the user’s role (e.g., `ADMIN`, `USER`).
- **AuthRequest.java** → What the client sends when logging in (username + password).
- **AuthResponse.java** → What we send back after login (JWT token + user info).

---

### 4. `service/`
Contains the **logic for authentication**.

- **AuthService.java** → Handles login, signup, and token generation.
- **UserDetailsServiceImpl.java** → Loads user information from the database for Spring Security.
- **PasswordEncoderConfig.java** → Defines how we encrypt passwords (using BCrypt).

---

### 5. `util/`
Helper classes for security.

- **CustomAccessDeniedHandler.java** → Sends a **403 Forbidden** when a logged-in user tries to access something they’re not allowed to.
- **SecurityUtils.java** → Utility methods (e.g., get the currently logged-in user).

---

## 🔄 How It Works (Very Simple)

1. **Login**
    - User sends username & password (`AuthRequest`).
    - `AuthService` checks the credentials.
    - If valid → `JwtTokenProvider` creates a JWT token.
    - We return (`AuthResponse`) with the token.

2. **Access Protected Endpoint**
    - User calls API with token in the header:
      ```
      Authorization: Bearer <token>
      ```  
    - `JwtAuthenticationFilter` checks the token.
    - If valid → request is allowed.
    - If not valid → `401 Unauthorized` or `403 Forbidden`.

---

## 🎯 Why This Matters

- Keeps our API **secure**.
- Makes sure only **authenticated users** can access protected resources.
- Ensures only users with the right **roles** (like ADMIN) can do sensitive actions.

