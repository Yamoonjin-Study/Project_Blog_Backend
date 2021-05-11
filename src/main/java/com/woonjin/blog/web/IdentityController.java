package com.woonjin.blog.web;

import com.woonjin.blog.application.IdentityAppService;
import com.woonjin.blog.domain.entity.User;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
public class IdentityController {

    private final IdentityAppService identityAppService;

    public IdentityController(IdentityAppService identityAppService) {
        this.identityAppService = identityAppService;
    }

    @PostMapping("/sign-up")
    @ResponseStatus(value = HttpStatus.OK)
    public String SignUp(@RequestBody User user) {
        identityAppService.signup(user);
        return user.getUsername() + "님 회원가입을 축하합니다.";
    }

    @PostMapping("/log-in")
    @ResponseStatus(value = HttpStatus.OK)
    public String LogIn(@RequestBody User user, HttpSession session) {
        Boolean result = identityAppService.login(user);

        if (result == true) {
            session.setAttribute("user", user);
            return user.getUsername() + "님 반갑습니다.";
        } else {
            return "아이디 비밀번호를 확인해주세요.";
        }
    }

    @PostMapping("/log-out")
    @ResponseStatus(value = HttpStatus.OK)
    public String LogOut(@RequestBody User user, HttpSession session) {
        identityAppService.logout(user);

        session.removeAttribute("user");
        return "로그아웃 되었습니다.";

    }
}
