package com.wetechb.CarwashProject.service;

import com.wetechb.CarwashProject.entity.Role;
import com.wetechb.CarwashProject.entity.Admin;
import com.wetechb.CarwashProject.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminDetailsSrvc implements UserDetailsService {

    private final AdminRepository adminRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Admin admin = adminRepository.findByEmail(email);

        if (admin != null) {
            return new org.springframework.security.core.userdetails.User(admin.getEmail(),
                    admin.getPassword(),
                    mapRolesToAuthorities(admin.getRoles()));
        }else{
            throw new UsernameNotFoundException("Invalid username or password.");
        }
    }

    private Collection< ? extends GrantedAuthority> mapRolesToAuthorities(List<Role> roles) {
        Collection < ? extends GrantedAuthority> mapRoles = roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
        return mapRoles;
    }
}
