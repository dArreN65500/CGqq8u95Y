// 代码生成时间: 2025-08-11 15:44:12
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
public class UserAuthenticationService {

    private final AuthenticationManager authenticationManager;

    // Constructor with dependency injection of the authentication manager
    public UserAuthenticationService(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    /**<ol>
     * Authenticates a user with the provided username and password.
     *
     * @param username The username of the user to authenticate.
     * @param password The password of the user to authenticate.
     * @return The Authentication object if successful, otherwise throws AuthenticationException.
     * @throws AuthenticationException If authentication fails.
     */
    public Authentication authenticate(String username, String password) throws AuthenticationException {
        try {
            // Create an authentication token with username and password
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(username, password);

            // Authenticate the token and return the result
            return authenticationManager.authenticate(authenticationToken);
        } catch (AuthenticationException e) {
            // Log the exception and rethrow it to be handled by the global exception handler
            throw e;
        }
    }
}
