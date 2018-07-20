package jinbro.security.exception;

import org.springframework.security.core.AuthenticationException;

public class InvalidJwtHeaderException extends AuthenticationException {

    public InvalidJwtHeaderException(String msg, Throwable t) {
        super(msg, t);
    }

    public InvalidJwtHeaderException(String msg) {
        super(msg);
    }
}
