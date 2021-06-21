package com.woonjin.blog.domain.repository;

import com.woonjin.blog.domain.entity.Visitor;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisitorRepository extends JpaRepository<Visitor, Integer> {

    List<Visitor> findByBlog_id(int id);
}
