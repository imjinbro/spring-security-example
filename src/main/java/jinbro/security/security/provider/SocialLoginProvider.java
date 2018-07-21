package jinbro.security.security.provider;

import jinbro.security.domain.Account;
import jinbro.security.domain.UserRole;
import jinbro.security.dto.SocialLoginDto;
import jinbro.security.security.AccountContext;
import jinbro.security.security.AccountService;
import jinbro.security.security.social.SocialFetchService;
import jinbro.security.security.social.SocialUserProperty;
import jinbro.security.security.tokens.PostAuthenticationToken;
import jinbro.security.security.tokens.SocialPreAuthorizationToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class SocialLoginProvider implements AuthenticationProvider {
    private static final Logger log = LoggerFactory.getLogger(SocialLoginProvider.class);

    @Autowired
    private AccountService accountService;

    @Autowired
    private SocialFetchService socialConnector;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        SocialPreAuthorizationToken token = (SocialPreAuthorizationToken) authentication;
        SocialLoginDto socialLoginDto = token.getDto();

        SocialUserProperty userProperty = socialConnector.getSocialUserInfo(socialLoginDto);

        Account account = accountService.findBySocialIdAndSocialProvider(userProperty.getUserId(), socialLoginDto.getProvider())
                .orElseGet(() -> accountService.create(new Account("SOCIAL_USER", "", userProperty.getUserNickname(), UserRole.USER, socialLoginDto.getProvider(), Long.valueOf(userProperty.getUserId()), userProperty.getProfileHref())));

        log.debug("account : {}", account.toString());
        return PostAuthenticationToken.getTokenFrom(AccountContext.fromAccountModel(account));
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return SocialPreAuthorizationToken.class.isAssignableFrom(authentication);
    }
}
