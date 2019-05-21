package com.github.maojx0630.common.feign;


import com.github.maojx0630.common.response.exception.StateEnum;
import com.github.maojx0630.common.response.exception.StateException;
import org.apache.commons.lang.StringUtils;

/**
 * 模式为传递用户id使用id从redis获取用户详细信息 若需要直接传递用户信息则修改此类
 * @author MaoJiaXing
 */
public class UserUtils {
    private static final ThreadLocal<String> THREAD_LOCAL = new ThreadLocal<>();
    public static final String KEY_USER_IN_HTTP_HEADER = "MJX-AUTO-ACCESS-USER-INFO";

    /**
     * 获取登陆用户若未登陆则抛出未登陆异常
     * <br/>
     *
     * @return java.lang.String
     * @author MaoJiaXing
     */
    public static String getUserId() {
        if (StringUtils.isBlank(THREAD_LOCAL.get())) {
            throw StateException.of(StateEnum.user_not_login);
        } else {
            return THREAD_LOCAL.get();
        }

    }

    /**
     * 获取登陆用户若未登陆则返回null
     * <br/>
     *
     * @return java.lang.String
     * @author MaoJiaXing
     */
    public static String getLoginUserId() {
        return THREAD_LOCAL.get();
    }

    /**
     * 设置用户code
     * <br/>
     *
     * @param userId 用户code
     * @author MaoJiaXing
     */
    public static void setUserId(String userId) {
        THREAD_LOCAL.set(userId);
    }

    public static void remove() {
        THREAD_LOCAL.remove();
    }
}
