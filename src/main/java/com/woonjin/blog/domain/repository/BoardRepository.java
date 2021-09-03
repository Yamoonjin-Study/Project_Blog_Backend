package com.woonjin.blog.domain.repository;

import com.woonjin.blog.domain.entity.Board;
import com.woonjin.blog.domain.entity.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Integer> {

    Board findById(int id);

    Board findByTitle(String title);

    List<Board> findByTitleContaining(String title);

    List<Board> findByUser(User writer);
}
