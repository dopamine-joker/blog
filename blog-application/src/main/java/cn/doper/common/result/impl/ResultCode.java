package cn.doper.common.result.impl;

import cn.doper.common.result.api.IModuleCode;
import cn.doper.common.result.api.IResultCode;
import cn.doper.common.result.manager.ResultManager;

public enum ResultCode implements IResultCode {

    /**
     * 通用模块
     */
    COMMON_OK(ModuleCode.COMMON, "200", "ok"),

    COMMON_FAILED(ModuleCode.COMMON, "500", "failed"),

    UNAUTHORIZED(ModuleCode.COMMON, "401", "token is invalid or expired or has no access"),

    FORBIDDEN(ModuleCode.COMMON, "403", "authority forbidden"),
    /**
     * 用户模块
     */
    USER_OK(ModuleCode.USER, "200", "ok"),
    USER_FAILED(ModuleCode.USER, "500", "failed"),
    USER_PHONE_DUPLICATE(ModuleCode.USER, "501", "phoneNumber duplicate"),
    USER_NAME_DUPLICATE(ModuleCode.USER, "502", "name duplicate"),
    /**
     * 博客模块
     */
    BLOG_OK(ModuleCode.BLOG, "200", "ok"),
    BLOG_FAILED(ModuleCode.BLOG, "400", "failed"),
    BLOG_USER_NO_EXISTS(ModuleCode.BLOG, "405", "user not exists"),
    BLOG_TITLE_EMPTY_ERR(ModuleCode.BLOG, "406", "title cannot be empty"),
    BLOG_CONTENT_EMPTY_ERR(ModuleCode.BLOG, "407", "content cannot bt empty"),
    BLOG_TAGS_EMPTY_ERR(ModuleCode.BLOG, "408", "tags cannot bt empty"),
    BLOG_NOT_EXISTS(ModuleCode.BLOG, "409", "blog not exists"),
    BLOG_INSERT_ERR(ModuleCode.BLOG, "410", "blog insert sql err"),
    BLOG_DELETE_ERR(ModuleCode.BLOG, "411", "blog delete err"),
    BLOG_TAGS_DELETE_ERR(ModuleCode.BLOG, "412", "blog tags delete err"),
    BLOG_UPDATE_ERR(ModuleCode.BLOG, "413", "blog update err"),
    BLOG_PARAM_ERR(ModuleCode.BLOG, "414", "blog update status param err"),
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
