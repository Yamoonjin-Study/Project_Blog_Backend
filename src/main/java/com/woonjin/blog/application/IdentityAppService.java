package com.woonjin.blog.application;

import com.woonjin.blog.domain.entity.User;
import com.woonjin.blog.domain.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class IdentityAppService {
    private final UserRepository userRepository;

    public IdentityAppService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Transactional
    public boolean login(User user){

        User userLogin = userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());

        if(userLogin == null){
            return false;
        }else {
            user.setStatus(User.Status.ACTIVE);
            userRepository.save(user);
            return true;
        }
    }

    @Transactional
    public void logout(User user){
        user.setStatus(User.Status.INACTIVE);
        userRepository.save(user);
    }

    @Transactional
    public void signup(User user){
        userRepository.save(user);
    }
}
