package cn.doper.common.result;

import cn.doper.common.result.api.IResultCode;
import cn.doper.common.result.impl.ResultCode;

/**
 * 响应实体
 * @author doper
 */
public class CommonResult<T> {

    private String code;

    private String message;

    private T data;

    CommonResult(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    /**
     * 通用响应成功
     * @param data
     * @return
     * @param <T>
     */
    public static<T> CommonResult<T> success(T data) {
        return new CommonResult<T>(ResultCode.COMMON_OK.getCode(), ResultCode.COMMON_OK.getMessage(), data);
    }

    /**
     * 通用响应成功
     * @param data
     * @param message
     * @return
     * @param <T>
     */
    public static<T> CommonResult<T> success(T data, String message) {
        return new CommonResult<T>(ResultCode.COMMON_OK.getCode(), message, data);
    }

    /**
     * 通用响应失败
     * @param data
     * @return
     * @param <T>
     */
    public static<T> CommonResult<T> failed(T data) {
        return new CommonResult<T>(ResultCode.COMMON_FAILED.getCode(), ResultCode.COMMON_FAILED.getMessage(), data);
    }

    /**
     * 通用响应失败
     * @param data
     * @param message
     * @return
     * @param <T>
     */
    public static<T> CommonResult<T> failed(T data, String message) {
        return new CommonResult<T>(ResultCode.COMMON_FAILED.getCode(), message, data);
    }

    /**
     * 自定义响应
     * @param resultCode
     * @param data
     * @return
     * @param <T>
     */
    public static <T> CommonResult<T> response(IResultCode resultCode, T data) {
        return new CommonResult<>(resultCode.getModuleResultCode(), resultCode.getMessage(), data);
    }

    public static <T> CommonResult<T> response(IResultCode resultCode, String message, T data) {
        return new CommonResult<>(resultCode.getModuleResultCode(), message, data);
    }

    /**
     * 权限失败
     * @param data
     * @return
     * @param <T>
     */
    public static <T> CommonResult<T> forbidden(T data) {
        return new CommonResult<T>(ResultCode.FORBIDDEN.getCode(), ResultCode.FORBIDDEN.getMessage(), data);
    }

    /**
     * 未认证失败
     * @param data
     * @return
     * @param <T>
     */
    public static <T> CommonResult<T> unauthorized(T data) {
        return new CommonResult<T>(ResultCode.UNAUTHORIZED.getCode(), ResultCode.UNAUTHORIZED.getMessage(), data);
    }



}
