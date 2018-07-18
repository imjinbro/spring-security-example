package jinbro.security.domain;

import javax.persistence.*;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;
    private String password;
    private String name;

    @Enumerated(EnumType.STRING)
    private UserRole role = UserRole.USER;

    private Long socialId;
    private String profileHref;

    public Account() {
    }

    public Account(String userId, String password, String name, UserRole role, Long socialId, String profileHref) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.role = role;
        this.socialId = socialId;
        this.profileHref = profileHref;
    }

    public Long getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public UserRole getRole() {
        return role;
    }

    public Long getSocialId() {
        return socialId;
    }

    public String getProfileHref() {
        return profileHref;
    }
}
