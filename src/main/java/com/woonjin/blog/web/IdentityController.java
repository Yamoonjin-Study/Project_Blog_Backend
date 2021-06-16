package com.woonjin.blog.web;

import com.woonjin.blog.application.dto.response.LogInResponse;
import com.woonjin.blog.application.dto.response.LogOutResponse;
import com.woonjin.blog.application.dto.response.MemberOutResponse;
import com.woonjin.blog.application.dto.response.SignUpResponse;
import com.woonjin.blog.application.service.IdentityAppService;
import com.woonjin.blog.application.dto.request.LogInRequest;
import com.woonjin.blog.application.dto.request.SignUpRequest;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = {"1. IdentityApp Service"})
public class IdentityController {

    private final IdentityAppService identityAppService;

    public IdentityController(IdentityAppService identityAppService) {
        this.identityAppService = identityAppService;
    }

    @PostMapping("/sign-up")
    @ResponseStatus(value = HttpStatus.OK)
    public SignUpResponse 회원가입(@RequestBody SignUpRequest signUpRequest) {
        return identityAppService.signup(signUpRequest);
    }

    @PostMapping("/log-in")
    @ResponseStatus(value = HttpStatus.OK)
    public LogInResponse 로그인(@RequestBody LogInRequest logInRequest) {
        return identityAppService.login(logInRequest);
    }

    @PostMapping("/log-out")
    @ResponseStatus(value = HttpStatus.OK)
    public LogOutResponse 로그아웃() {
        return identityAppService.logout();
    }

    @PostMapping("/member-out")
    @ResponseStatus(value = HttpStatus.OK)
    public MemberOutResponse 회원탈퇴() {
        return identityAppService.memberout();
    }
}