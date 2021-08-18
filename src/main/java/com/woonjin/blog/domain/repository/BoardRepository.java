package com.woonjin.blog.domain.repository;

import com.woonjin.blog.domain.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Integer> {

    Board findById(int id);

    Board findByTitle(String title);
}
