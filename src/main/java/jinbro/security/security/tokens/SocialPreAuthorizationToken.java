package jinbro.security.security.tokens;

import jinbro.security.dto.SocialLoginDto;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class SocialPreAuthorizationToken extends UsernamePasswordAuthenticationToken {


    private SocialPreAuthorizationToken(Object principal, Object credentials) {
        super(principal, credentials);
    }

    // 인터페이스로 지정해두고 쓰려면 DTO도 그렇고 팩토리 메소드 클래스를 사용하는 것이 좋을듯함
    public static SocialPreAuthorizationToken createToken(SocialLoginDto socialLoginDto) {
        return new SocialPreAuthorizationToken(socialLoginDto, socialLoginDto.getToken());
    }

    public SocialLoginDto getDto() {
        return (SocialLoginDto) super.getPrincipal();
    }
}
