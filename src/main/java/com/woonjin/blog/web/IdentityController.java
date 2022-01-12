package com.woonjin.blog.web;

import com.woonjin.blog.application.dto.request.LogInCheckRequest;
import com.woonjin.blog.application.dto.request.UpdateUserRequest;
import com.woonjin.blog.application.dto.request.WithdrawalRequest;
import com.woonjin.blog.application.dto.response.LogInCheckResponse;
import com.woonjin.blog.application.dto.response.LogInResponse;
import com.woonjin.blog.application.dto.response.LogOutResponse;
import com.woonjin.blog.application.dto.response.UpdateUserResponse;
import com.woonjin.blog.application.dto.response.WithdrawalResponse;
import com.woonjin.blog.application.dto.response.SignUpResponse;
import com.woonjin.blog.application.service.IdentityAppService;
import com.woonjin.blog.application.dto.request.LogInRequest;
import com.woonjin.blog.application.dto.request.SignUpRequest;
import com.woonjin.blog.domain.entity.User;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = {"1. IdentityApp Service"})
public class IdentityController {

    private final IdentityAppService identityAppService;

    public IdentityController(IdentityAppService identityAppService) {
        this.identityAppService = identityAppService;
    }

    @PostMapping("/sign-up")
    public SignUpResponse SignUp(@RequestBody SignUpRequest signUpRequest) {
        return this.identityAppService.signup(signUpRequest);
    }

    @PostMapping("/log-in")
    public LogInResponse LogIn(@RequestBody LogInRequest logInRequest) {
        return this.identityAppService.login(logInRequest);
    }

    @PostMapping("/log-in/check")
    public LogInCheckResponse LogInCheck(@RequestBody LogInCheckRequest token) {
        return this.identityAppService.loginCheck(token.getToken());
    }

    @GetMapping("/log-out")
    public LogOutResponse LogOut() {
        return this.identityAppService.logout();
    }

    @PostMapping("/update")
    public UpdateUserResponse UpdateUser(@RequestBody UpdateUserRequest updateUserRequest) {
        return this.identityAppService.updateUser(updateUserRequest);
    }

    @PostMapping("/withdrawal")
    public WithdrawalResponse Withdrawal(@RequestBody WithdrawalRequest withdrawalRequest) {
        return this.identityAppService.withdrawal(withdrawalRequest);
    }

    @GetMapping("/userinfo/{id}")
    public User ShowUserInfo(@PathVariable int id){
        return this.identityAppService.showUserInfo(id);
    }
}