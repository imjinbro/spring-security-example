package jinbro.security.security.filters;

import jinbro.security.security.HeaderTokenParser;
import jinbro.security.security.handlers.JwtAuthenticationFailureHandler;
import jinbro.security.security.tokens.JwtPreProcessingToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    private static final Logger log = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    private HeaderTokenParser headerTokenParser;
    private JwtAuthenticationFailureHandler failureHandler;

    public JwtAuthenticationFilter(RequestMatcher matcher, HeaderTokenParser headerTokenParser, JwtAuthenticationFailureHandler failureHandler) {
        super(matcher);
        this.headerTokenParser = headerTokenParser;
        this.failureHandler = failureHandler;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        String tokenPayLoad = headerTokenParser.parse(request);
        JwtPreProcessingToken preProcessingToken = new JwtPreProcessingToken(tokenPayLoad);
        return super.getAuthenticationManager().authenticate(preProcessingToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        /* 다음 진행을 위해서 끊는게 아니라 다음으로 넘어가게 - 요청이 계속해서 진행되게끔 */
        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
        securityContext.setAuthentication(authResult);
        SecurityContextHolder.setContext(securityContext);
        chain.doFilter(request, response);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        SecurityContextHolder.clearContext();
        failureHandler.onAuthenticationFailure(request, response, failed);
    }
}
