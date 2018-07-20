package jinbro.security.domain;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class UserRoleTest {

    @Test
    public void match() {
        String name = UserRole.getName("ROLE_USER");
        assertThat(name, is("ROLE_USER"));
    }
}