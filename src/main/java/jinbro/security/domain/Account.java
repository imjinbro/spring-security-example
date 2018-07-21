package jinbro.security.domain;

import jinbro.security.dto.SocialProviders;

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

    @Enumerated(EnumType.STRING)
    private SocialProviders provider;

    private Long socialId;
    private String profileHref;

    public Account() {
    }

    public Account(String userId, String password, String name, UserRole role, SocialProviders provider, Long socialId, String profileHref) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.role = role;
        this.provider = provider;
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

    public SocialProviders getProvider() {
        return provider;
    }

    public Long getSocialId() {
        return socialId;
    }

    public String getProfileHref() {
        return profileHref;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", role=" + role.getRoleName() +
                ", provider=" + provider.getProviderName() +
                ", socialId=" + socialId +
                ", profileHref='" + profileHref + '\'' +
                '}';
    }
}
