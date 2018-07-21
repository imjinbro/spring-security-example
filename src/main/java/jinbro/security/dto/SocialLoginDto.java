package jinbro.security.dto;

public class SocialLoginDto {

    private SocialProviders provider;
    private String token;

    public SocialLoginDto() {
    }

    public SocialProviders getProvider() {
        return provider;
    }

    public SocialLoginDto setProvider(SocialProviders provider) {
        this.provider = provider;
        return this;
    }

    public String getToken() {
        return token;
    }

    public SocialLoginDto setToken(String token) {
        this.token = token;
        return this;
    }
}
