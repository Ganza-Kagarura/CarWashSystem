package com.wetechb.CarwashProject.service;

import com.wetechb.CarwashProject.dto.UserDto;
import com.wetechb.CarwashProject.entity.User;
import com.wetechb.CarwashProject.repository.RoleRepository;
import com.wetechb.CarwashProject.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface UserService {
    void saveUser(UserDto userDto);

    User findUserByEmail(String email);

    List    <UserDto> findAllUsers();
}
