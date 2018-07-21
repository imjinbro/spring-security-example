package jinbro.security.dto;

import com.fasterxml.jackson.annotation.JsonValue;
import jinbro.security.security.social.KakaoUserProperty;
import jinbro.security.security.social.SocialUserProperty;

public enum SocialProviders {
    KAKAO("https://kapi.kakao.com/v2/user/me", KakaoUserProperty.class);

    private String endPoint;
    private Class<? extends SocialUserProperty> propertyMetaClass;

    SocialProviders(String endPoint, Class<? extends SocialUserProperty> propertyMetaClass) {
        this.endPoint = endPoint;
        this.propertyMetaClass = propertyMetaClass;
    }

    @JsonValue
    public String getProviderName() {
        return name();
    }

    public String getEndPoint() {
        return endPoint;
    }

    public Class<? extends SocialUserProperty> getPropertyMetaClass() {
        return propertyMetaClass;
    }
}
