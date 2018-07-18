package jinbro.security.dto;

public class FormLoginDto {

    private String userId;
    private String password;

    public FormLoginDto() {
    }

    public FormLoginDto(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public FormLoginDto setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public FormLoginDto setPassword(String password) {
        this.password = password;
        return this;
    }
}
