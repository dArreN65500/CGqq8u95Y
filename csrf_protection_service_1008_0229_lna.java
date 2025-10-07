// 代码生成时间: 2025-10-08 02:29:25
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * CSRF Protection Service
 * This service provides the necessary mechanisms to protect against Cross-Site Request Forgery (CSRF) attacks.
 * It generates a CSRF token on each request and verifies it on subsequent requests.
 */
@Service
public class CsrfProtectionService {

    private final HttpServletRequest request;
    private final HttpServletResponse response;

    public CsrfProtectionService(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    /**
     * Generates a CSRF token and sets it as a cookie.
     * This token will be used to verify the authenticity of subsequent requests.
     *
     * @return The generated CSRF token.
     */
    public String generateCsrfToken() {
        String csrfToken = UUID.randomUUID().toString();
        Cookie cookie = new Cookie("CSRF-TOKEN", csrfToken);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        response.addCookie(cookie);
        return csrfToken;
    }

    /**
     * Verifies the CSRF token from the request against the one stored in the cookie.
     *
     * @param csrfRequestToken The CSRF token sent in the request.
     * @param csrfCookieToken The CSRF token stored in the cookie.
     * @return true if the tokens match, false otherwise.
     */
    public boolean verifyCsrfToken(@CookieValue("CSRF-TOKEN") String csrfRequestToken, String csrfCookieToken) {
        return csrfRequestToken != null && csrfRequestToken.equals(csrfCookieToken);
    }

    /**
     * REST endpoint to generate a new CSRF token.
     * This endpoint is used to get a new CSRF token which is then used in subsequent requests.
     */
    @GetMapping("/get-csrf-token")
    public String getCsrfToken() {
        return generateCsrfToken();
    }

    /**
     * REST endpoint to perform an action that requires CSRF protection.
     * This endpoint checks the CSRF token to ensure the request is valid.
     *
     * @param csrfToken The CSRF token sent with the request.
     * @return A success message if the CSRF token is valid, an error message otherwise.
     */
    @PostMapping("/perform-action")
    public String performActionWithCsrfProtection(@RequestParam("csrf_token") String csrfToken) {
        String cookieToken = request.getCookie("CSRF-TOKEN").getValue();
        if (!verifyCsrfToken(csrfToken, cookieToken)) {
            return "CSRF token mismatch. Please try again.";
        } else {
            return "Action performed successfully.";
        }
    }
}
