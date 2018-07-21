package jinbro.security.security.social;

import jinbro.security.dto.SocialLoginDto;
import jinbro.security.dto.SocialProviders;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SocialFetchServiceProd implements SocialFetchService {
    private static final String HEADER_KEY = "Authorization";
    private static final String HEADER_PREFIX = "Bearer ";

    @Override
    public SocialUserProperty getSocialUserInfo(SocialLoginDto socialLoginInfo) {
        SocialProviders provider = socialLoginInfo.getProvider();

        RestTemplate template = new RestTemplate();
        HttpEntity<String> entity = new HttpEntity<>(generateHeader(socialLoginInfo.getToken()));
        return template.exchange(provider.getEndPoint(), HttpMethod.GET, entity, provider.getPropertyMetaClass()).getBody();
    }

    private HttpHeaders generateHeader(String token) {
        HttpHeaders header = new HttpHeaders();
        header.add(HEADER_KEY, generateHeaderContent(token));
        return header;
    }

    private String generateHeaderContent(String token) {
        StringBuilder builder = new StringBuilder();
        builder.append(HEADER_PREFIX).append(token);
        return builder.toString();
    }
}
