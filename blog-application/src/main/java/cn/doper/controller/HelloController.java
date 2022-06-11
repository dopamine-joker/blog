package cn.doper.controller;

import cn.doper.annotation.LoginUser;
import cn.doper.common.context.UserContext;
import cn.doper.common.result.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/hello")
public class HelloController {

    @PostMapping("/test")
    @PreAuthorize("hasAuthority('sys:hello:test')")
    public CommonResult<?> testPost() {
        return CommonResult.success("testPost");
    }

    @GetMapping("/test")
//    @PreAuthorize("hasAuthority('test')")
    public CommonResult<?> testGet() {
        return CommonResult.success("testGet");
    }

    @PostMapping("/info")
    public CommonResult<?> testInfo(@LoginUser UserContext userContext) {
        return CommonResult.success(userContext);
    }

}
