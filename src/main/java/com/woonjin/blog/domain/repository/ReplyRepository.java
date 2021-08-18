package com.woonjin.blog.domain.repository;

import com.woonjin.blog.domain.entity.Board;
import com.woonjin.blog.domain.entity.Reply;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyRepository extends JpaRepository<Reply, Integer> {

    Reply findById(int id);

    List<Reply> findByBoard(Board board);
//
//    List<Reply> findByTop_reply(int reply_id);
}