package com.woonjin.blog.domain.repository;

import com.woonjin.blog.domain.entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Chat, Integer> {

}
