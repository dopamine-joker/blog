package cn.doper.common.result.impl;

import cn.doper.common.result.api.IModuleCode;
import cn.doper.common.result.api.IResultCode;
import cn.doper.common.result.manager.ResultManager;

public enum ResultCode implements IResultCode {

    // 通用模块结果
    COMMON_OK(ModuleCode.COMMON, "200", "ok"),

    COMMON_FAILED(ModuleCode.COMMON, "500", "failed"),

    UNAUTHORIZED(ModuleCode.COMMON, "401", "token is invalid or expired"),

    FORBIDDEN(ModuleCode.COMMON, "403", "authority forbidden"),
    // 用户模块
    USER_OK(ModuleCode.USER, "200", "ok"),
    USER_FAILED(ModuleCode.USER, "500", "failed"),
    USER_PHONE_DUPLICATE(ModuleCode.USER, "501", "phoneNumber duplicate"),
    USER_NAME_DUPLICATE(ModuleCode.USER, "502", "name duplicate"),
    ;

    private final String code;
    private final String message;

    ResultCode(IModuleCode moduleCode, String code, String message) {
        this.code = code;
        this.message = message;
        ResultManager.register(moduleCode, this);
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
