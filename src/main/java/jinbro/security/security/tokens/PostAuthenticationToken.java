package jinbro.security.security.tokens;

import jinbro.security.security.AccountContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class PostAuthenticationToken extends UsernamePasswordAuthenticationToken {

    private PostAuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }

    public static PostAuthenticationToken getTokenFrom(AccountContext accountContext) {
        return new PostAuthenticationToken(accountContext, accountContext.getPassword(), accountContext.getAuthorities());
    }

    public String getUserId() {
        AccountContext accountContext = (AccountContext) super.getPrincipal();
        return accountContext.getUsername();
    }
}
