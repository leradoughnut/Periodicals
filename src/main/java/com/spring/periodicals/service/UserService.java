package com.spring.periodicals.service;

import com.spring.periodicals.dto.UserDTO;
import com.spring.periodicals.entity.User;
import com.spring.periodicals.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {

    List<User> findAll();

    void saveUser(UserDTO userDTO);


    //boolean register (UserDTO userDTO){};
}
