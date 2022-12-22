package com.training.games;

import com.training.games.controller.dto.CreateGameDto;
import com.training.games.controller.dto.SignupRequest;
import com.training.games.service.GameService;
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
    @Autowired
    private GameService gameService;

    public static void main(String[] args) {
        SpringApplication.run(GamesSpringbootApplication.class, args);
    }

    @Bean
    @Transactional
    CommandLineRunner commandLineRunner() {
        return args -> {
            userService.signup(new SignupRequest("boris", "qwerty"));

            gameService.addGame(new CreateGameDto("Counter-strike condition zero", "est un jeu vidéo de tir à la première personne multijoueur en ligne basé sur le jeu d'équipe. Développé par Turtle Rock Studios et édité par Valve Software, le jeu est sorti le 1er mars 2004 sur Windows"));
            gameService.addGame(new CreateGameDto("CS 1.6", "A connu depuis sa sortie officielle le 8 novembre 2000 un important succès. Début 2010, Counter-Strike était encore le jeu de tir à la première personne le plus joué en ligne, devant des jeux plus récents tels que son évolution"));
            gameService.addGame(new CreateGameDto("Counter-Strike: Source", "généralement abrégé par CS:S ou CSS, est un jeu vidéo développé par Valve Software. Il s'agit d'une évolution de Counter-Strike utilisant le moteur Source. Comme dans l'original, Counter-Strike: Source met en scène une équipe d'anti-terroristes contre une équipe de terroristes dans une série de rounds. Chaque round est gagné soit en effectuant la mission (explosion de la bombe ou sauvetage d'otages) soit en éliminant tous les membres de l'équipe opposée. Counter Strike: Source est l'un des jeux phare du sport électronique."));

        };
    }

}
