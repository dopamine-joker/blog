package cn.doper.common.result.manager;

import cn.doper.common.result.api.IModuleCode;
import cn.doper.common.result.api.IResultCode;
import com.google.common.base.Preconditions;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import java.util.concurrent.ConcurrentHashMap;

public class ResultManager {

//    private static final BiMap<String, IResultCode> GLOBAL_RESULT_CODE_MAP = HashBiMap.create();
    private static final ConcurrentHashMap<IResultCode, String> GLOBAL_RESULT_CODE_MAP = new ConcurrentHashMap<>();

    private static final ConcurrentHashMap<IResultCode, String> GLOBAL_RESULT_MSG_MAP = new ConcurrentHashMap<>();

    public static void register(IModuleCode moduleCode, IResultCode resultCode) {
        String code = generateResultCode(moduleCode, resultCode);
        Preconditions.checkArgument(!GLOBAL_RESULT_CODE_MAP.containsKey(resultCode), "错误码重复:" + code);
        GLOBAL_RESULT_CODE_MAP.put(resultCode, code);
        String msg = generatorMsg(moduleCode, resultCode);
        GLOBAL_RESULT_MSG_MAP.put(resultCode, msg);
    }

    private static String generateResultCode(IModuleCode moduleCode, IResultCode resultCode) {
        return moduleCode.getCode() + resultCode.getCode();
    }

    private static String generatorMsg(IModuleCode moduleCode, IResultCode resultCode) {
        return moduleCode.getName() + ":" + resultCode.getMessage();
    }

    public static String getCode(IResultCode resultCode) {
        return GLOBAL_RESULT_CODE_MAP.get(resultCode);
    }

    public static String getMsg(IResultCode resultCode) {
        return GLOBAL_RESULT_MSG_MAP.get(resultCode);
    }
}
