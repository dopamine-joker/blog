package cn.doper.controller;

import cn.doper.common.CommonResult;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
