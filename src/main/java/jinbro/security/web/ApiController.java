package jinbro.security.web;

import jinbro.security.security.tokens.PostAuthenticationToken;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {

    @GetMapping("/exam")
    @PreAuthorize("hasRole('ROLE_USER')") // 스프링 익스프레션 랭귀지 - 해당 ROLE 네임을 가지고 있어야 메소드 진입이 가능함
    public String getUserName(Authentication authentication) { // authResult - PostAuthenticationToken
        PostAuthenticationToken token = (PostAuthenticationToken) authentication;
        return token.getUserId();
    }
}
