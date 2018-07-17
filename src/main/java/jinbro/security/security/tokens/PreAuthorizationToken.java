package jinbro.security.security.tokens;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class PreAuthorizationToken extends UsernamePasswordAuthenticationToken {

    public PreAuthorizationToken(String userId, String password) {
        super(userId, password);

    }

    public String getUserId() {
        return String.valueOf(super.getPrincipal());
    }

    public String getUserPassword() {
        return String.valueOf(super.getCredentials());
    }
}
