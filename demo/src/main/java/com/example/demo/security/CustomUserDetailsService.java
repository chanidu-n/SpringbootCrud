package com.example.demo.security;

import com.example.demo.model.User;
import com.example.demo.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // You should store usernames in your DB; for now, matching by name
        User user = userRepo.findAll().stream()
                .filter(u -> u.getName().equals(username))
                .findFirst()
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new org.springframework.security.core.userdetails.User(
                user.getName(),
                "{noop}password", // Replace with real password field and encoder
                new ArrayList<>()
        );
    }
}
