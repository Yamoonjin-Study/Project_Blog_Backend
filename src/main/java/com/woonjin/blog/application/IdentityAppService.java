package com.woonjin.blog.application;

import com.woonjin.blog.application.dto.LogInRequest;
import com.woonjin.blog.application.dto.SignUpRequest;
import com.woonjin.blog.domain.entity.User;
import com.woonjin.blog.domain.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

@Service
public class IdentityAppService {
    private final UserRepository userRepository;
    private final HttpSession session;

    public IdentityAppService(UserRepository userRepository, HttpSession session){
        this.userRepository = userRepository;
        this.session = session;
    }


    @Transactional
    public String login(LogInRequest logInRequest){

        User userLogin = userRepository.findByUsernameAndPassword(logInRequest.getUsername(), logInRequest.getPassword());

        if(userLogin == null){
            return "아이디 비밀번호를 확인해주세요.";
        }else {
            userLogin.setStatus(User.Status.ACTIVE);
            userRepository.save(userLogin);
            session.setAttribute("user", userLogin);
            return logInRequest.getUsername() + "님, 반갑습니다.";
        }
    }

    @Transactional
    public String logout(){
        User userLogout = (User) session.getAttribute("user");
        userLogout.setStatus(User.Status.INACTIVE);
        userRepository.save(userLogout);
        session.removeAttribute("user");
        return userLogout.getUsername()+"님, 로그아웃 되었습니다.";
    }

    @Transactional
    public String signup(SignUpRequest signUpRequest){
        if(signUpRequest.getPassword().equals(signUpRequest.getPasswordCheck())){
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
            return signUpRequest.getUsername() + "님, 회원가입을 축하합니다.";
        }
        else {
            return "비밀번호를 다시 확인해주세요.";
        }
    }

    @Transactional
    public String memberout(){
        User memberOut = (User) session.getAttribute("user");
        userRepository.delete(memberOut);
        session.removeAttribute("user");
        return "탈퇴되었습니다. 재가입시 서비스를 다시 이용하실 수 있습니다.";
    }
}
