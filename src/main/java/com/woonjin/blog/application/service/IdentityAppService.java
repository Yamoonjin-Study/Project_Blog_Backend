package com.woonjin.blog.application.service;

import com.woonjin.blog.application.dto.request.LogInRequest;
import com.woonjin.blog.application.dto.request.SignUpRequest;
import com.woonjin.blog.application.dto.response.LogInResponse;
import com.woonjin.blog.application.dto.response.LogOutResponse;
import com.woonjin.blog.application.dto.response.MemberOutResponse;
import com.woonjin.blog.application.dto.response.SignUpResponse;
import com.woonjin.blog.domain.entity.User;
import com.woonjin.blog.domain.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

@Service
public class IdentityAppService {

    private final UserRepository userRepository;
    private final HttpSession session;

    public IdentityAppService(UserRepository userRepository, HttpSession session) {
        this.userRepository = userRepository;
        this.session = session;
    }


    @Transactional
    public LogInResponse login(LogInRequest logInRequest) {

        User userLogin = userRepository
            .findByUsernameAndPassword(logInRequest.getUsername(), logInRequest.getPassword());

        if (userLogin == null) {
            return LogInResponse.of("login fail", userLogin);
        } else {
            userLogin.activate(userLogin);
            userRepository.save(userLogin);
            session.setAttribute("user", userLogin);
            return LogInResponse.of("login success", userLogin);
        }
    }

    @Transactional
    public LogOutResponse logout() {
        User userLogout = (User) session.getAttribute("user");
        userLogout.inactivate(userLogout);
        userRepository.save(userLogout);
        session.removeAttribute("user");
        return LogOutResponse.of("logout success", userLogout);
    }

    @Transactional
    public SignUpResponse signup(SignUpRequest signUpRequest) {
        if (signUpRequest.getPassword().equals(signUpRequest.getPasswordCheck())) {
            User userSignup = this.userRepository.save(
                User.of(
                    signUpRequest.getEmail(),
                    signUpRequest.getPassword(),
                    signUpRequest.getUsername(),
                    signUpRequest.getPhone(),
                    User.Status.INACTIVE,
                    User.RoleType.USER
                )
            );
            return SignUpResponse.of("signup success", signUpRequest);
        } else {
            return SignUpResponse.of("signup fail", signUpRequest);
        }
    }

    @Transactional
    public MemberOutResponse memberout() {
        User memberOut = (User) session.getAttribute("user");
        userRepository.delete(memberOut);
        session.removeAttribute("user");
        return MemberOutResponse.of("memberout success", memberOut);
    }
}
