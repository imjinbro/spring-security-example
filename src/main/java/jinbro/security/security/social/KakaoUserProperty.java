package jinbro.security.security.social;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;

public class KakaoUserProperty implements SocialUserProperty {
    @JsonProperty("id")
    private long id;

    @JsonProperty("kakao_account.email")
    private String email;

    @JsonProperty("properties")
    private Map<String, String> properties = new HashMap<>();

    public KakaoUserProperty() {
    }

    public KakaoUserProperty setId(long id) {
        this.id = id;
        return this;
    }

    public KakaoUserProperty setEmail(String email) {
        this.email = email;
        return this;
    }

    public KakaoUserProperty setProperties(Map<String, String> properties) {
        this.properties = properties;
        return this;
    }

    public long getId() {
        return id;
    }

    public Map<String, String> getProperties() {
        return properties;
    }

    /* getter만 일관성 있게 작성하면 됨 - API마다 각각의 json key로 오기 때문에 각각은 맞춰줘야함 */
    @Override
    public String getUserId() {
        return String.valueOf(id);
    }

    @Override
    public String getUserNickname() {
        return properties.get("nickname");
    }

    @Override
    public String getProfileHref() {
        return properties.get("thumbnail_image");
    }

    @Override
    public String getEmail() {
        return email;
    }
}
