package jinbro.security.security.social;

import jinbro.security.dto.SocialLoginDto;

public interface SocialFetchService {
    /* 엔드포인트로 요청해서 정보를 뺴오는 서비스 - 액세스토큰을 가지고 OAuth 서버에 가나봄 */
    SocialUserProperty getSocialUserInfo(SocialLoginDto socialLoginInfo);
}
