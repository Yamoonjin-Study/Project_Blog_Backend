package com.woonjin.blog.web;

import com.woonjin.blog.application.IdentityAppService;
import com.woonjin.blog.application.dto.LogInRequest;
import com.woonjin.blog.application.dto.SignUpRequest;
import com.woonjin.blog.domain.entity.User;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@Api(tags = { "1. IdentityApp Service" })
public class IdentityController {

    private final IdentityAppService identityAppService;

    public IdentityController(IdentityAppService identityAppService) {
        this.identityAppService = identityAppService;
    }

    @PostMapping("/sign-up")
    @ResponseStatus(value = HttpStatus.OK)
    public String 회원가입(@RequestBody SignUpRequest signUpRequest) {
        return identityAppService.signup(signUpRequest);
    }

    @PostMapping("/log-in")
    @ResponseStatus(value = HttpStatus.OK)
    public String 로그인(@RequestBody LogInRequest logInRequest) {
        return identityAppService.login(logInRequest);
    }

    @PostMapping("/log-out")
    @ResponseStatus(value = HttpStatus.OK)
    public String 로그아웃() {
        return identityAppService.logout();
    }

    @PostMapping("/member-out")
    @ResponseStatus(value = HttpStatus.OK)
    public String 회원탈퇴() {
        return identityAppService.memberout();
    }
}