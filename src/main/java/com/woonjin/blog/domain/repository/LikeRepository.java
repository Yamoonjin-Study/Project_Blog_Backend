package com.woonjin.blog.domain.repository;

import com.woonjin.blog.domain.entity.Board;
import com.woonjin.blog.domain.entity.Like;
import com.woonjin.blog.domain.entity.Reply;
import com.woonjin.blog.domain.entity.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, Integer> {

    List<Like> findByBoard(Board board);

    Like findByBoardAndUser(Board board, User user);

    List<Like> findByReply(Reply reply);

    Like findByReplyAndUser(Reply reply, User user);
}
