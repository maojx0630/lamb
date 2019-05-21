package com.github.maojx0630.common.response.advice;

import com.github.maojx0630.common.response.exception.StateEnum;
import com.github.maojx0630.common.response.exception.StateException;
import com.github.maojx0630.common.response.result.ResponseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Objects;

@RestControllerAdvice
public class ResponseResultAdvice implements ResponseBodyAdvice<Object> {

    private static final Logger LOGGER= LoggerFactory.getLogger(ResponseResultAdvice.class);

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return !(methodParameter.getDeclaringClass().isAnnotationPresent(IgnoreResponseResultAdvice.class) ||
                Objects.requireNonNull(methodParameter.getMethod()).isAnnotationPresent(IgnoreResponseResultAdvice.class));
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        ResponseResult responseResult;
        if (Objects.isNull(o)) {
            responseResult = new ResponseResult<>(StateEnum.success);
        } else if (o instanceof ResponseResult) {
            responseResult = (ResponseResult) o;
        } else {
            responseResult = new ResponseResult<>(StateEnum.success, o);
        }
        return responseResult;
    }

    @ExceptionHandler(StateException.class)
    public ResponseResult stateException(StateException exception){
        return new ResponseResult(exception);
    }

    @ExceptionHandler(Exception.class)
    public ResponseResult exception(Exception e){
        ResponseResult responseResult=test(e,0);
        if(responseResult == null){
            LOGGER.error("发生了异常，异常堆栈如下",e);
            return new ResponseResult<>(StateEnum.error, e.getMessage());
        }else{
            return responseResult;
        }
    }

    private ResponseResult test(Throwable throwable,int number){
        if(number>3||throwable==null){
            return null;
        }else if(throwable instanceof StateException){
            return new ResponseResult((StateException)throwable);
        }else {
            number++;
            return test(throwable.getCause(),number);
        }

    }
}
