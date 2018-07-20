package jinbro.security.security.provider;

import jinbro.security.security.AccountContext;
import jinbro.security.security.JwtManager;
import jinbro.security.security.tokens.JwtPreProcessingToken;
import jinbro.security.security.tokens.PostAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private JwtManager jwtManager;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        JwtPreProcessingToken preProcessingToken = (JwtPreProcessingToken) authentication;
        return PostAuthenticationToken.getTokenFrom(jwtManager.decodeToken(preProcessingToken.getToken()));
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtPreProcessingToken.class.isAssignableFrom(authentication);
    }
}
