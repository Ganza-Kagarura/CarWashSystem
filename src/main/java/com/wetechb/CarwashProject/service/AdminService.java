package com.wetechb.CarwashProject.service;

import com.wetechb.CarwashProject.dto.UserDto;
import com.wetechb.CarwashProject.entity.Admin;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface AdminService {
    void saveUser(UserDto userDto);

    Admin findUserByEmail(String email);

    List    <UserDto> findAllUsers();
}
