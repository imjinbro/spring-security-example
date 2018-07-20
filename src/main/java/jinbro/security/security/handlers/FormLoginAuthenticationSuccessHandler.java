package jinbro.security.security.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import jinbro.security.dto.TokenDto;
import jinbro.security.security.AccountContext;
import jinbro.security.security.JwtManager;
import jinbro.security.security.tokens.PostAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class FormLoginAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private JwtManager jwtManager;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        PostAuthenticationToken token = (PostAuthenticationToken) authentication;
        AccountContext accountContext = (AccountContext) token.getPrincipal();
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        response.getWriter().write(objectMapper.writeValueAsString(generateTokenDto(jwtManager.generateToken(accountContext))));
    }

    private TokenDto generateTokenDto(String tokenMessage) {
        return new TokenDto().setTokenMessage(tokenMessage);
    }
}
