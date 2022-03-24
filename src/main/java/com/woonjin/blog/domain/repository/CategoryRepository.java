package com.woonjin.blog.domain.repository;

import com.woonjin.blog.domain.entity.Category;
import com.woonjin.blog.domain.entity.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    List<Category> findByUser(User user);

    List<String> findNameByUser(User user);

    Category findByName(String name);
}
