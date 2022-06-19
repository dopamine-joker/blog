package cn.doper.constant;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public enum BlogStatus {
    UNPUBLISHED(0),
    PUBLISHED(1),
    INVISIBLE(2),
    ;


    public final static Set<Integer> statusCodeSet;

    static {
        statusCodeSet = Arrays.stream(BlogStatus.values())
                .map(BlogStatus::getStatus)
                .collect(Collectors.toSet());
    }

    public static boolean containCode(int code) {
        return statusCodeSet.contains(code);
    }

    private final int status;

    BlogStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
