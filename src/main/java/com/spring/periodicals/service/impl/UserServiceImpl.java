package com.spring.periodicals.service.impl;

import com.spring.periodicals.dto.UserDTO;
import com.spring.periodicals.entity.Role;
import com.spring.periodicals.entity.Status;
import com.spring.periodicals.entity.User;
import com.spring.periodicals.repository.UserRepository;
import com.spring.periodicals.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    UserRepository userRepository;
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void saveUser(UserDTO userDTO) {
        userRepository.save(buildUserFromDTO(userDTO));
    }

    public User buildUserFromDTO(UserDTO userDTO){
        return User.builder()
                .name(userDTO.getName())
                .email(userDTO.getEmail())
                .password(bCryptPasswordEncoder.encode(userDTO.getPassword()))
                .role(Role.USER)
                .status(Status.ACTIVE)
                .build();
    }
}
