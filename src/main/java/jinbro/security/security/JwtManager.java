package jinbro.security.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class JwtManager {
    private static final Logger log = LoggerFactory.getLogger(JwtManager.class);
    private static final String SIGN_KEY = "jwtTest";
    private static final String CLAIM_USER_NAME = "USER_NAME";
    private static final String CLAIM_USER_ROLE = "USER_ROLE";

    public String generateToken(AccountContext context) {
        String token = null;
        try {
            token = JWT.create()
                    .withIssuer(context.getUsername())
                    .withClaim(CLAIM_USER_NAME, context.getUsername())
                    .withClaim(CLAIM_USER_ROLE, context.getAccountRole())
                    .sign(generateAlgorithm());
        } catch (Exception e) {
            log.error("jwt generate exception : {}", e.getMessage());
        }
        return token;
    }

    public AccountContext decodeToken(String encodedToken) {
        AccountContext accountContext = null;
        JWTVerifier verifier = JWT.require(generateAlgorithm()).build();
        try {
            DecodedJWT decodedJWT = verifier.verify(encodedToken);
            accountContext = AccountContext.fromJwtClaimValue(
                    decodedJWT.getClaim(CLAIM_USER_NAME).asString(),
                    "",
                    decodedJWT.getClaim(CLAIM_USER_ROLE).asString());
        } catch (JWTVerificationException e) {
            throw new JWTVerificationException("변조된 JWT 입니다.");
        }
        return accountContext;
    }

    private Algorithm generateAlgorithm() {
        return Algorithm.HMAC256(SIGN_KEY.getBytes());
    }
}
