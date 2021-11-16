package com.woonjin.blog.application.service;

import com.woonjin.blog.application.dto.request.LogInRequest;
import com.woonjin.blog.application.dto.request.SignUpRequest;
import com.woonjin.blog.application.dto.response.LogInCheckResponse;
import com.woonjin.blog.application.dto.response.LogInResponse;
import com.woonjin.blog.application.dto.response.LogOutResponse;
import com.woonjin.blog.application.dto.response.WithdrawalResponse;
import com.woonjin.blog.application.dto.response.SignUpResponse;
import com.woonjin.blog.config.security.JwtTokenProvider;
import com.woonjin.blog.domain.entity.User;
import com.woonjin.blog.domain.entity.User.RoleType;
import com.woonjin.blog.domain.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class IdentityAppService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final static Logger Log = Logger.getGlobal();

    public IdentityAppService(
        UserRepository userRepository,
        PasswordEncoder passwordEncoder,
        JwtTokenProvider jwtTokenProvider
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Transactional
    public User getAuthenticationUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User authUser = (User) authentication.getPrincipal();
        int id = authUser.getId();
        User user = this.userRepository.findById(id);
        Log.info("getAuthenticationUser : " + user);
        return user;
    }

    @Transactional
    public LogInResponse login(LogInRequest logInRequest) {

        User userLogin = this.userRepository
            .findByEmail(logInRequest.getEmail());

        if (userLogin == null) {
            Log.warning("Login Fail");
            return LogInResponse.of("Login Fail", null, null);
        } else {
            if (!this.passwordEncoder.matches(logInRequest.getPassword(),
                userLogin.getPassword())) {
                Log.warning("Login Fail");
                return LogInResponse.of("Login Fail", null, userLogin);
            } else {

                List<RoleType> roles = new ArrayList<>();
                roles.add(userLogin.getRole());

                String token = this.jwtTokenProvider.createToken(String.valueOf(userLogin.getId()),
                    roles);
                Authentication authentication = this.jwtTokenProvider.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(authentication);
                Log.info(
                    "이름 : " + SecurityContextHolder.getContext().getAuthentication().getName());
                Log.info("Login Success and token : " + token + " user : "
                    + getAuthenticationUser());
                return LogInResponse.of("Login Success", token, userLogin);
            }
        }
    }

    public LogInCheckResponse loginCheck(String token) {
        if (this.jwtTokenProvider.validateToken(token) == true) {
            return LogInCheckResponse.of(true, "IsLogin");
        } else {
            return LogInCheckResponse.of(false, "IsNotLogin");
        }

    }

    @Transactional
    public LogOutResponse logout() {
        SecurityContextHolder.getContext().setAuthentication(null);

        this.Log.info("Logout Success");
        return LogOutResponse.of("Logout Success");
    }

    @Transactional
    public SignUpResponse signup(SignUpRequest signUpRequest) {
        if (signUpRequest.getPassword().equals(signUpRequest.getPasswordCheck())) {
            this.userRepository.save(
                User.of(
                    signUpRequest.getEmail(),
                    this.passwordEncoder.encode(signUpRequest.getPassword()),
                    signUpRequest.getUsername(),
                    signUpRequest.getNickname(),
                    signUpRequest.getPhone(),
                    User.Status.INACTIVE,
                    User.RoleType.USER
                )
            );

            this.Log.info("Signup Success");
            return SignUpResponse.of("Signup Success", signUpRequest);
        } else {

            this.Log.warning("Signup Fail");
            return SignUpResponse.of("Signup Fail", signUpRequest);
        }
    }

    @Transactional
    public WithdrawalResponse withdrawal() {
        User withdrawalUser = this.getAuthenticationUser();
        this.userRepository.delete(withdrawalUser);

        SecurityContextHolder.getContext().setAuthentication(null);

        this.Log.info("Withdrawal Success");
        return WithdrawalResponse.of("Withdrawal Success", withdrawalUser);
    }

    @Transactional(readOnly = true)
    public User showUserInfo(int id) {
        User userFindById = this.userRepository.findById(id);
        return userFindById;
    }
}

