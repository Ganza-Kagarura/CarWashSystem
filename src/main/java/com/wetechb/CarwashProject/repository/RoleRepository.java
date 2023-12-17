package com.wetechb.CarwashProject.repository;

import com.wetechb.CarwashProject.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
