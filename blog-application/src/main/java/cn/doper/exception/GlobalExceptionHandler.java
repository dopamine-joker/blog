package cn.doper.exception;

import cn.doper.common.result.CommonResult;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = BusinessException.class)
    public CommonResult<?> handle(BusinessException e) {
        log.error("捕获{}异常，错误码:{}， 内容:{}", e.getClass(), e.getErrCode(), e.getMessage());
        return CommonResult.response(e.getErrResultCode(), null);
    }

    @ResponseBody
    @ExceptionHandler(value = ExpiredJwtException.class)
    public CommonResult<?> handleJWTExpired(ExpiredJwtException e) {
        log.error("捕获{}异常，内容:{}", e.getClass(), e.getMessage());
        return CommonResult.unauthorized("登陆过期，请重新登陆", "");
    }

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public CommonResult<?> handleCommon(Exception e) {
        log.error("捕获未知{}异常， 内容:{}", e.getClass(), e.getMessage());
        return CommonResult.failed(null);
    }

    /**
     * 权限异常，使用PreAuthorize的情况下会被全局异常处理器捕获
     * @param e
     * @throws AccessDeniedException
     */
    @ExceptionHandler(value = AccessDeniedException.class)
    public CommonResult<?> handleAccessDenied(AccessDeniedException e) throws AccessDeniedException {
        throw e;
    }

    @ResponseBody
    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    public CommonResult<?> handleMethodArgumentTypeMismatchException(Exception e) {
        log.error("捕获{}异常， 内容:{}", e.getClass(), e.getMessage());
        return CommonResult.failed("参数错误，请检查参数");
    }

}
