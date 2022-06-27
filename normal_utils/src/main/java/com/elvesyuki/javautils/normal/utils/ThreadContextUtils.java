package com.elvesyuki.javautils.normal.utils;


import com.elvesyuki.javautils.normal.factory.ThreadContextFactory;
import com.elvesyuki.javautils.normal.object.ThreadPayload;

/**
 * @author ：luohuan
 * @date ：Created in 2022/5/7/007 16:43
 * @description：
 * @modified By：
 */
public class ThreadContextUtils {

    private ThreadContextUtils() {
    }

    public static final String USER_TOKEN_KEY = ThreadContextUtils.class.getName() + "_USER_TOKEN_KEY";

    @SuppressWarnings({"unchecked"})
    public static ThreadPayload<Long> getUserTokenPayload() {

        ThreadPayload<Long> threadPayload = (ThreadPayload<Long>) ThreadContextFactory.get(USER_TOKEN_KEY);
        if (threadPayload == null) {
            threadPayload = new ThreadPayload<>();
            bindUserTokenPayload(threadPayload);
        }
        return threadPayload;
    }

    public static void bindUserTokenPayload(ThreadPayload<Long> payload) {
        if (payload != null) {
            ThreadContextFactory.put(USER_TOKEN_KEY, payload);
        }
    }

    public static void unbindUserTokenPayload() {
        ThreadContextFactory.remove(USER_TOKEN_KEY);
    }

}
