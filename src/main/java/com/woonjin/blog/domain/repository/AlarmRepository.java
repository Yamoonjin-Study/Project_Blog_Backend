package com.woonjin.blog.domain.repository;

import com.woonjin.blog.domain.entity.Alarm;
import com.woonjin.blog.domain.entity.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlarmRepository extends JpaRepository<Alarm, Integer> {

    List<Alarm> findByReceiver(User receiver);

    Alarm findById(int id);
}
