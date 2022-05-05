package cn.doper.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @PostMapping("/test")
    @PreAuthorize("hasAuthority('test')")
    public String testPost() {
        return "testPost";
    }

    @GetMapping("/test")
    public String testGet() {
        return "testGet";
    }
}
