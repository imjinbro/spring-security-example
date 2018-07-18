package jinbro.security.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class JwtFactory {
    private static final Logger log = LoggerFactory.getLogger(JwtFactory.class);
    private static String signingKey = "jwtTest";

    public String generateToken(AccountContext context) {
        String token = null;
        try {
            token = JWT.create()
                    .withIssuer(context.getUsername())
                    .withClaim("USER_ROLES", context.getAccountRole())
                    .sign(generateAlgorithm());
        } catch (Exception e) {
            log.error("jwt generate exception : {}", e.getMessage());
        }
        return token;
    }

    private Algorithm generateAlgorithm() {
        return Algorithm.HMAC256(signingKey.getBytes());
    }
}
