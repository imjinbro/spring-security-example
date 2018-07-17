package jinbro.security.security.tokens;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class PreAuthorizationToken extends UsernamePasswordAuthenticationToken {

    public PreAuthorizationToken(String userName, String password) {
        super(userName, password);

    }

    public String getUserName() {
        return String.valueOf(super.getPrincipal());
    }

    public String getUserPassword() {
        return String.valueOf(super.getCredentials());
    }
}
