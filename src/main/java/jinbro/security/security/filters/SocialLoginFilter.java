package jinbro.security.security.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import jinbro.security.dto.SocialLoginDto;
import jinbro.security.security.tokens.SocialPreAuthorizationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SocialLoginFilter extends AbstractAuthenticationProcessingFilter {

    private AuthenticationSuccessHandler successHandler;

    private SocialLoginFilter(String defaultFilterProcessesUrl) {
        super(defaultFilterProcessesUrl);
    }

    public static SocialLoginFilter createSocialFilter(String url, AuthenticationSuccessHandler successHandler) {
        SocialLoginFilter socialLoginFilter = new SocialLoginFilter(url);
        socialLoginFilter.successHandler = successHandler;
        return socialLoginFilter;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        SocialLoginDto socialLoginDto = new ObjectMapper().readValue(request.getReader(), SocialLoginDto.class);
        SocialPreAuthorizationToken preAuthenticationToken = SocialPreAuthorizationToken.createToken(socialLoginDto);
        return super.getAuthenticationManager().authenticate(preAuthenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        successHandler.onAuthenticationSuccess(request, response, authResult);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        super.unsuccessfulAuthentication(request, response, failed);
    }
}
