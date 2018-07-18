package jinbro.security.security.provider;

import jinbro.security.domain.Account;
import jinbro.security.security.AccountContext;
import jinbro.security.security.AccountService;
import jinbro.security.security.tokens.PostAuthenticationToken;
import jinbro.security.security.tokens.PreAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@Component
public class FormLoginAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private AccountService accountService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /*
       인증객체를 만들어줌
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        PreAuthenticationToken preAuthenticationToken = (PreAuthenticationToken) authentication;
        Account account = accountService.findByUserId(preAuthenticationToken.getUserId());

        if (isCorrectPassword(preAuthenticationToken.getUserPassword(), account)) {
            return PostAuthenticationToken.getTokenFrom(AccountContext.fromAccountModel(account));
        }
        throw new NoSuchElementException("인증정보가 정확하지않습니다.");
    }

    /*
       프로바이더가 어떤 인증객체를 서포트할지 정하는 메소드
       설정된 클래스는 여기 필터로 걸리게되어있음 - 위의 메소드
     */
    @Override
    public boolean supports(Class<?> authentication) {
        return PreAuthenticationToken.class.isAssignableFrom(authentication);
    }

    private boolean isCorrectPassword(String password, Account account) {
        return passwordEncoder.matches(password, account.getPassword());
    }
}
