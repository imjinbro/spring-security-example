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
    private UserRole role;

    private Long socialId;
    private String profileHref;

    public Account() {

    }


}
