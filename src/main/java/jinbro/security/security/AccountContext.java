package jinbro.security.security;

import jinbro.security.domain.Account;
import jinbro.security.domain.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class AccountContext extends User {

    private AccountContext(String userId, String password, Collection<? extends GrantedAuthority> authorities) {
        super(userId, password, authorities);
    }

    public static AccountContext fromAccountModel(Account account) {
        return new AccountContext(account.getUserId(), account.getPassword(), parseAuthority(account.getRole()));
    }

    private static List<SimpleGrantedAuthority> parseAuthority(UserRole role) {
        return Collections.singletonList(new SimpleGrantedAuthority(role.getRoleName()));
    }

    public String getAccountRole() {
        return new ArrayList<>(super.getAuthorities()).get(0).getAuthority();
    }
}
