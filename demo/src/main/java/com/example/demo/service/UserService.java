package com.example.demo.service;

import com.example.demo.dto.UserDTO;
import com.example.demo.model.User;
import com.example.demo.repo.UserRepo;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.modelmapper.TypeToken;

import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class UserService {
    @Autowired
   private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    public List<UserDTO> getAllUsers() {
        List<User> userList = userRepo.findAll();
        return modelMapper.map(userList, new TypeToken<List<UserDTO>>() {}.getType());
    }

    public UserDTO saveUser(UserDTO userDTO){
        userRepo.save(modelMapper.map(userDTO,User.class));
        return userDTO;
    }

    public UserDTO saveAddress(UserDTO userDTO){
        userRepo.save(modelMapper.map(userDTO,User.class));
        return userDTO;
    }

    @Service
    public class CustomUserDetailsService implements UserDetailsService {
        @Autowired
        private UserRepo userRepo;

        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            User user = userRepo.findByName(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            return new org.springframework.security.core.userdetails.User(
                    user.getName(),
                    user.getPassword(),
                    Collections.singletonList(new SimpleGrantedAuthority(user.getRole()))
            );
        }
    }



}
