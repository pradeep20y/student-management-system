package com.example.student_manage.security;

import com.example.student_manage.model.Users;
import com.example.student_manage.repository.JpaRepo;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final JpaRepo jpaRepo;

    public CustomUserDetailsService(JpaRepo jpaRepo) {
        this.jpaRepo = jpaRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = jpaRepo.findByUser(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new org.springframework.security.core.userdetails.User(
                user.getUser(),
                user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority(user.getRole().name())));
    }
}
