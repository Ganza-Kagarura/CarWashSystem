package com.wetechb.CarwashProject.repository;

import com.wetechb.CarwashProject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
