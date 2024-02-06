package cn.doper.common.context;

public class UserContextHolder {

    private static final ThreadLocal<UserContext> LOCAL = new InheritableThreadLocal<>();

    private static final UserContext EMPTY_CONTEXT = new UserContext();

    public static UserContext get() {
        return LOCAL.get() == null ? EMPTY_CONTEXT : LOCAL.get();
    }

    public static UserContext set(Long id, String userName) {
        UserContext userContext = new UserContext(id, userName);
        LOCAL.set(userContext);
        return userContext;
    }

    public static void remove() {
        LOCAL.remove();
    }

}
