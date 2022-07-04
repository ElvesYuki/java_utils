package com.elvesyuki.javautils.normal.utils;

import com.elvesyuki.javautils.normal.object.ThreadPayload;
import org.springframework.util.ObjectUtils;

/**
 * @author ：luohuan
 * @date ：Created in 2022/3/30/030 9:23
 * @description：获取登录用户类
 * @modified By：
 */
public class UserProfileUtils {

    private UserProfileUtils() {
    }

    public static Long getLoginUserId() {
        ThreadPayload<Long> userTokenPayload = ThreadContextUtils.getUserTokenPayload();
        if (ObjectUtils.isEmpty(userTokenPayload) || ObjectUtils.isEmpty(userTokenPayload.getPayload())) {
            throw new RuntimeException("错误");
        }
        return userTokenPayload.getPayload();
    }

}
