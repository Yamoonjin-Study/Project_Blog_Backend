package com.woonjin.blog.domain.repository;

import com.woonjin.blog.domain.entity.GuestBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestBookRepository extends JpaRepository<GuestBook, Integer> {

}
