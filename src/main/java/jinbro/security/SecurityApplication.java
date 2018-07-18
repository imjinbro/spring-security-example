package jinbro.security;

import jinbro.security.domain.Account;
import jinbro.security.domain.AccountRepository;
import jinbro.security.domain.UserRole;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class SecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurityApplication.class, args);
    }

    @Bean
    CommandLineRunner test(AccountRepository accountRepository, PasswordEncoder encoder) {
        return args -> {
            Account account = new Account("colin", encoder.encode("1234"), "colin", UserRole.USER, 1123213L, "http://www.naver.com");
            accountRepository.save(account);
        };
    }
}
