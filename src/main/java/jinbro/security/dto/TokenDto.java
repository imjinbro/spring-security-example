package jinbro.security.dto;

public class TokenDto {
    private String tokenMessage;

    public TokenDto() {
    }

    public String getTokenMessage() {
        return tokenMessage;
    }

    public TokenDto setTokenMessage(String tokenMessage) {
        this.tokenMessage = tokenMessage;
        return this;
    }
}
