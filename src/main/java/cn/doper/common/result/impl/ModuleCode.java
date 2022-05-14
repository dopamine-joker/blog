package cn.doper.common.result.impl;

import cn.doper.common.result.api.IModuleCode;

public enum ModuleCode implements IModuleCode {

    COMMON("000", "通用模块"),
    USER("001", "用户模块")
    ;

    private final String moduleCode;

    private final String moduleName;

    ModuleCode(String moduleCode, String moduleName) {
        this.moduleCode = moduleCode;
        this.moduleName = moduleName;
    }

    @Override
    public String getCode() {
        return this.moduleCode;
    }

    @Override
    public String getName() {
        return this.moduleName;
    }
}
