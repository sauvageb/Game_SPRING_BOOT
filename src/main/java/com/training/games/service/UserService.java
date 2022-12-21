package com.training.games.service;

import com.training.games.controller.dto.SignupRequest;
import com.training.games.controller.exception.UserAlreadyExistException;
import com.training.games.repository.UserRepository;
import com.training.games.repository.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder encoder;

    public void signup(SignupRequest dto) throws UserAlreadyExistException {

        boolean alreadyExist = userRepository.existsByUsername(dto.getUsername());

        if(alreadyExist){
            throw new UserAlreadyExistException(dto.getUsername());
        }else {
            User newUser = new User(dto.getUsername(), encoder.encode(dto.getPassword()));
            userRepository.save(newUser);
        }

    }
}
