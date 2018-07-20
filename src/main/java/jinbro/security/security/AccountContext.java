package jinbro.security.security;

import jinbro.security.domain.Account;
import jinbro.security.domain.UserRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class AccountContext extends User {
    private static final Logger log = LoggerFactory.getLogger(AccountContext.class);

    private AccountContext(String userId, String password, Collection<? extends GrantedAuthority> authorities) {
        super(userId, password, authorities);
    }

    public static AccountContext fromAccountModel(Account account) {
        return new AccountContext(account.getUserId(), account.getPassword(), parseAuthority(account.getRole()));
    }

    public static AccountContext fromJwtClaimValue(String userId, String password, String userRoleName) {
        return new AccountContext(userId, password, parseAuthority(userRoleName));
    }

    private static List<SimpleGrantedAuthority> parseAuthority(UserRole role) {
        return Collections.singletonList(new SimpleGrantedAuthority(role.getRoleName()));
    }

    private static List<SimpleGrantedAuthority> parseAuthority(String userRoleName) {
        log.debug("request token userRoleName : {}", userRoleName);
        return Collections.singletonList(new SimpleGrantedAuthority(UserRole.getName(userRoleName)));
    }

    public String getAccountRole() {
        return new ArrayList<>(super.getAuthorities()).get(0).getAuthority();
    }

    @Override
    public String toString() {
        return super.getUsername() + ", " + super.getPassword();
    }
}
