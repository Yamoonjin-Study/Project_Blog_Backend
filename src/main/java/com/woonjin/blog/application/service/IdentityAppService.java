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

    public IdentityAppService(
        UserRepository userRepository,
        HttpSession session
    ) {
        this.userRepository = userRepository;
        this.session = session;
    }


    @Transactional
    public LogInResponse login(LogInRequest logInRequest) {

        User userLogin = this.userRepository
            .findByEmailAndPassword(logInRequest.getEmail(), logInRequest.getPassword());

        if (userLogin == null) {
            return LogInResponse.of("Login Fail", userLogin);
        } else {
            userLogin.activate(userLogin);
            this.userRepository.save(userLogin);
            this.session.setAttribute("user", userLogin);
            return LogInResponse.of("Login Success", userLogin);
        }
    }

    @Transactional
    public LogOutResponse logout() {
        User userLogout = (User) this.session.getAttribute("user");
        userLogout.inactivate(userLogout);
        this.userRepository.save(userLogout);
        this.session.removeAttribute("user");
        return LogOutResponse.of("Logout Success", userLogout);
    }

    @Transactional
    public SignUpResponse signup(SignUpRequest signUpRequest) {
        if (signUpRequest.getPassword().equals(signUpRequest.getPasswordCheck())) {
            User userSignup = this.userRepository.save(
                User.of(
                    signUpRequest.getEmail(),
                    signUpRequest.getPassword(),
                    signUpRequest.getUsername(),
                    signUpRequest.getNick_name(),
                    signUpRequest.getPhone(),
                    User.Status.INACTIVE,
                    User.RoleType.USER
                )
            );
            return SignUpResponse.of("Signup Success", signUpRequest);
        } else {
            return SignUpResponse.of("Signup Fail", signUpRequest);
        }
    }

    @Transactional
    public MemberOutResponse memberout() {
        User memberOut = (User) this.session.getAttribute("user");
        this.userRepository.delete(memberOut);
        this.session.removeAttribute("user");
        return MemberOutResponse.of("Memberout Success", memberOut);
    }
}
