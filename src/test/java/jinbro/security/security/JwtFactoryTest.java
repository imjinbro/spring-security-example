package jinbro.security.security;

import jinbro.security.domain.Account;
import jinbro.security.domain.UserRole;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class JwtFactoryTest {
    private static final Logger log = LoggerFactory.getLogger(JwtFactoryTest.class);

    @Autowired
    private JwtFactory jwtFactory;

    private AccountContext accountContext;

    @Before
    public void setUp() throws Exception {
        Account account = new Account("colin", "1234", "colin", UserRole.USER, 12312412L, "http://www.naver.com");
        accountContext = AccountContext.fromAccountModel(account);
    }

    @Test
    public void generateToken() {
        log.debug("token value : {}", jwtFactory.generateToken(accountContext));
    }
}