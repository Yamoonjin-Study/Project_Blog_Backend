package com.woonjin.blog.web;

import com.woonjin.blog.application.dto.response.LogInResponse;
import com.woonjin.blog.application.dto.response.LogOutResponse;
import com.woonjin.blog.application.dto.response.WithdrawalResponse;
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
    @ResponseStatus(value = HttpStatus.CREATED)
    public SignUpResponse SignUp(@RequestBody SignUpRequest signUpRequest) {
        return identityAppService.signup(signUpRequest);
    }

    @PutMapping("/log-in")
    @ResponseStatus(value = HttpStatus.OK)
    public LogInResponse LogIn(@RequestBody LogInRequest logInRequest) {
        return identityAppService.login(logInRequest);
    }

    @PutMapping("/log-out")
    @ResponseStatus(value = HttpStatus.OK)
    public LogOutResponse LogOut() {
        return identityAppService.logout();
    }

    @PostMapping("/withdrawal")
    @ResponseStatus(value = HttpStatus.OK)
    public WithdrawalResponse Withdrawal() {
        return identityAppService.withdrawal();
    }
}