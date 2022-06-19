package cn.doper.common.result;

import cn.doper.common.result.api.IResultCode;
import cn.doper.common.result.impl.ResultCode;

/**
 * 响应实体
 *
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
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> CommonResult<T> success(T data) {
        return response(ResultCode.COMMON_OK, data);
    }

    /**
     * 通用响应成功
     *
     * @param data
     * @param message
     * @param <T>
     * @return
     */
    public static <T> CommonResult<T> success(T data, String message) {
        return response(ResultCode.COMMON_OK, message, data);
    }

    /**
     * 通用响应失败
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> CommonResult<T> failed(T data) {
        return response(ResultCode.COMMON_FAILED, data);
    }

    /**
     * 通用响应失败
     *
     * @param data
     * @param message
     * @param <T>
     * @return
     */
    public static <T> CommonResult<T> failed(T data, String message) {
        return response(ResultCode.COMMON_FAILED, message, data);
    }

    /**
     * 自定义响应
     *
     * @param resultCode
     * @param data
     * @param <T>
     * @return
     */
    public static <T> CommonResult<T> response(IResultCode resultCode, T data) {
        return new CommonResult<>(resultCode.getModuleResultCode(), resultCode.getMessage(), data);
    }

    public static <T> CommonResult<T> response(IResultCode resultCode, String message, T data) {
        return new CommonResult<>(resultCode.getModuleResultCode(), message, data);
    }

    /**
     * 权限失败
     *
     * @param message
     * @param <T>
     * @return
     */
    public static <T> CommonResult<T> forbidden(String message, T data) {
        return response(ResultCode.FORBIDDEN, message, data);
    }

    /**
     * 未认证失败
     *
     * @param message
     * @param <T>
     * @return
     */
    public static <T> CommonResult<T> unauthorized(String message, T data) {
        return response(ResultCode.UNAUTHORIZED, message, data);
    }


}
