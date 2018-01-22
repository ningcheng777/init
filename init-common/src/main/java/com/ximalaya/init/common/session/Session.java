package com.ximalaya.init.common.session;

/**
 * @author ningcheng
 * @date 2017/11/23
 */
public class Session {

    private static ThreadLocal<User> local = new ThreadLocal<User>();

    public static void setUser(User user) {
        local.set(user);
    }

    public static User getUser() {
        return local.get();
    }
}
