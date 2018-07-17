package jinbro.security.security;

import jinbro.security.domain.Account;
import jinbro.security.domain.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class AccountService implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        return accountRepository.findByUserId(userId).map(AccountContext::fromAccountModel).orElseThrow(NoSuchElementException::new);
    }

    public Account findByUserId(String userId) {
        return accountRepository.findByUserId(userId).orElseThrow(NoSuchElementException::new);
    }
}
