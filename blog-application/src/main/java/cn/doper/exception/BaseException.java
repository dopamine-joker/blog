package cn.doper.exception;

import cn.doper.common.result.api.IResultCode;

public abstract class BaseException extends RuntimeException {

    private static final long serialVersionUID = 4926810639562834492L;

    private final IResultCode errResultCode;

    public BaseException(IResultCode errResultCode) {
        super(errResultCode.getModuleResultMsg());
        this.errResultCode = errResultCode;
    }

    public BaseException(IResultCode errResultCode, Throwable cause) {
        super(errResultCode.getModuleResultMsg(), cause);
        this.errResultCode = errResultCode;
    }

    public IResultCode getErrResultCode() {
        return errResultCode;
    }

    public String getErrCode() {
        return errResultCode.getModuleResultCode();
    }

}
