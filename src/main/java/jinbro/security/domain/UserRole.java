package jinbro.security.domain;

import jinbro.security.exception.InvalidUserRoleException;

import java.util.Arrays;

public enum UserRole {
    ADMIN("ROLE_ADMIN"),
    USER("ROLE_USER");

    private String roleName;

    UserRole(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }

    public static String getName(String userRoleName) {
        return Arrays.stream(UserRole.values())
                .filter(userRole -> userRole.roleName.equalsIgnoreCase(userRoleName))
                .map(userRole -> userRole.roleName)
                .findFirst()
                .orElseThrow(() -> new InvalidUserRoleException("존재하지않는 유저 역할입니다."));
    }
}
