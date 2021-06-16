package com.woonjin.blog.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("test/{id}")
    public String test(@PathVariable int id) {
        return "test 중 입니다." + id + "님 반갑습니다.";
    }
}
