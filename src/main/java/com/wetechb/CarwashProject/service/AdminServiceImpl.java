package com.wetechb.CarwashProject.service;

import com.wetechb.CarwashProject.dto.UserDto;
import com.wetechb.CarwashProject.entity.Role;
import com.wetechb.CarwashProject.entity.Admin;
import com.wetechb.CarwashProject.repository.RoleRepository;
import com.wetechb.CarwashProject.repository.AdminRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminServiceImpl implements AdminService {
    private final AdminRepository adminRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminServiceImpl(AdminRepository userRepository,
                            RoleRepository roleRepository,
                            PasswordEncoder passwordEncoder) {
        this.adminRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUser(UserDto userDto) {
        Admin user = new Admin();
        user.setName(userDto.getFirstName() + " " + userDto.getLastName());
        user.setEmail(userDto.getEmail());
        // encrypt the password using spring security
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        Role role = roleRepository.findByName("ROLE_ADMIN");
        if(role == null){
            role = checkRoleExist();
        }
        user.setRoles(Arrays.asList(role));
        adminRepository.save(user);
    }

    @Override
    public Admin findUserByEmail(String email) {
        return adminRepository.findByEmail(email);
    }

    @Override
    public List<UserDto> findAllUsers() {
        List<Admin> users = adminRepository.findAll();
        return users.stream()
                .map((user) -> mapToUserDto(user))
                .collect(Collectors.toList());
    }

    private UserDto mapToUserDto(Admin user){
        UserDto userDto = new UserDto();
        String[] str = user.getName().split(" ");
        userDto.setFirstName(str[0]);
        userDto.setLastName(str[1]);
        userDto.setEmail(user.getEmail());
        return userDto;
    }

    private Role checkRoleExist(){
        Role role = new Role();
        role.setName("ROLE_ADMIN");
        return roleRepository.save(role);
    }
}
