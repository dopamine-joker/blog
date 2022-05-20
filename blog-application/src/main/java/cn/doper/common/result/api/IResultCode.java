package cn.doper.common.result.api;

import cn.doper.common.result.manager.ResultManager;

public interface IResultCode {
    String getCode();
    String getMessage();

    default String getModuleResultCode() {
        return ResultManager.getCode(this);
    }

    default String getModuleResultMsg() {
        return ResultManager.getMsg(this);
    }

}
