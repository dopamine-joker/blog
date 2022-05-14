package cn.doper.exception;

import cn.doper.common.result.api.IResultCode;

public class BusinessException extends BaseException {
    private static final long serialVersionUID = 5140606701243925945L;

    public BusinessException(IResultCode resultCode) {
        super(resultCode);
    }

    public BusinessException(IResultCode resultCode, Throwable cause) {
        super(resultCode, cause);
    }

}
