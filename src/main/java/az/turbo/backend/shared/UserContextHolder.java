package az.turbo.backend.shared;

import az.turbo.backend.users.domain.model.Role;

public class UserContextHolder {
    private static final ThreadLocal<Long> USER_CONTEXT = new ThreadLocal<>();
    private static final ThreadLocal<Role> ROLE_CONTEXT = new ThreadLocal<>();

    public static void setUserId(long userId) {
        USER_CONTEXT.set(userId);
    }

    public static long getUserId() {
        return USER_CONTEXT.get();
    }

    public static void setRole(Role role) {
        ROLE_CONTEXT.set(role);
    }

    public static Role getRole() {
        return ROLE_CONTEXT.get();
    }

    public static void clear() {
        USER_CONTEXT.remove();
        ROLE_CONTEXT.remove();
    }
}
