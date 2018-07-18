package jinbro.security.security.tokens;

import jinbro.security.dto.FormLoginDto;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class PreAuthenticationToken extends UsernamePasswordAuthenticationToken {

    public PreAuthenticationToken(String userId, String password) {
        super(userId, password);
    }

    public PreAuthenticationToken(FormLoginDto loginDto) {
        super(loginDto.getUserId(), loginDto.getPassword());
    }

    public String getUserId() {
        return String.valueOf(super.getPrincipal());
    }

    public String getUserPassword() {
        return String.valueOf(super.getCredentials());
    }
}
