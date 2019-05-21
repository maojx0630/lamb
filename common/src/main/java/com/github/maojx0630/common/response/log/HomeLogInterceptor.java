package com.github.maojx0630.common.response.log;

import com.alibaba.fastjson.JSON;
import com.github.maojx0630.common.response.exception.StateEnum;
import com.github.maojx0630.common.response.exception.StateException;
import com.github.maojx0630.common.response.result.ResponseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;

public class HomeLogInterceptor implements HandlerInterceptor {

    private static final Logger LOGGER= LoggerFactory.getLogger(HomeLogInterceptor.class);

    private static final ThreadLocal<Long> LONG_THREAD_LOCAL = new ThreadLocal<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof ResourceHttpRequestHandler) {
            response.setHeader("Content-type", "application/json;charset=UTF-8");
            response.getOutputStream().write(JSON.toJSONString(new ResponseResult(StateException.
                    of(StateEnum.no_found, "{" +request.getRequestURI() + "}")))
                    .getBytes(StandardCharsets.UTF_8));
            return false;
        }
        LONG_THREAD_LOCAL.set(System.currentTimeMillis());
        LOGGER.info("▁▂▃▄▅▆▇█本次请求路径 {} █▇▆▅▄▃▂▁ ", request.getRequestURI());
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                Exception ex) {
        long begin=LONG_THREAD_LOCAL.get();
        LONG_THREAD_LOCAL.remove();
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        LOGGER.info("▁▂▃▄▅▆▇█执行完成 {} 方法█▇▆▅▄▃▂▁ 耗时：{} ms", handlerMethod.getMethod().getName(), System.currentTimeMillis()-begin);
    }

}
