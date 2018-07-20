package jinbro.security.security;

import jinbro.security.exception.InvalidJwtHeaderException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Component
public class HeaderTokenParser {
    private static final String AUTHORIZATION = "Authorization";
    private static final String HEADER_PREFIX = "Bearer ";

    public String parse(HttpServletRequest request) {
        String jwtWithPrefix = request.getHeader(AUTHORIZATION);
        if (Objects.isNull(jwtWithPrefix) ||  jwtWithPrefix.length() < HEADER_PREFIX.length()) {
            throw new InvalidJwtHeaderException("올바른 토큰 정보가 아닙니다");
        }
        return jwtWithPrefix.substring(HEADER_PREFIX.length(), jwtWithPrefix.length());
    }
}
