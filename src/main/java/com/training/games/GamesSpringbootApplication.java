package com.training.games;

import com.training.games.controller.dto.SignupRequest;
import com.training.games.repository.UserRepository;
import com.training.games.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GamesSpringbootApplication {

    @Autowired
    private UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(GamesSpringbootApplication.class, args);
    }

    @Bean
    @Transactional
    CommandLineRunner commandLineRunner(){
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                userService.signup(new SignupRequest("boris", "qwerty"));
            }
        };
    }

}
