package com.training.games.service;

import com.training.games.controller.dto.SignupRequest;
import com.training.games.controller.exception.UserAlreadyExistException;
import com.training.games.repository.RoleRepository;
import com.training.games.repository.UserRepository;
import com.training.games.repository.entity.Role;
import com.training.games.repository.entity.RoleEnum;
import com.training.games.repository.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder encoder;

    public void signup(SignupRequest dto) throws UserAlreadyExistException {

        boolean alreadyExist = userRepository.existsByUsername(dto.getUsername());

        if(alreadyExist){
            throw new UserAlreadyExistException(dto.getUsername());
        }else {
            User newUser = new User(dto.getUsername(), encoder.encode(dto.getPassword()));
            // Define role
            Role roleUser = roleRepository.findByName(RoleEnum.ROLE_USER);
            List<Role> roleList = Arrays.asList(roleUser);
            newUser.setRoleList(roleList);
            userRepository.save(newUser);
        }

    }
}
