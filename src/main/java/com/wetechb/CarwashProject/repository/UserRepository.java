package com.wetechb.CarwashProject.repository;

import com.wetechb.CarwashProject.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Admin, Long> {
    Admin findByEmail(String email);
}
