package jinbro.security.exception;

import org.springframework.security.core.AuthenticationException;

public class InvalidUserRoleException extends AuthenticationException {

    public InvalidUserRoleException(String msg, Throwable t) {
        super(msg, t);
    }

    public InvalidUserRoleException(String msg) {
        super(msg);
    }
}
