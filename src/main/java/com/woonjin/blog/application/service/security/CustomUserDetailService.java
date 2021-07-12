package com.woonjin.blog.application.service.security;

import com.woonjin.blog.domain.repository.UserRepository;
import com.woonjin.blog.web.exception.CUserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetails loadUserByUsername(String userPk) {
        return userRepository.findById(Integer.valueOf(userPk)).orElseThrow(CUserNotFoundException::new);
    }
}
