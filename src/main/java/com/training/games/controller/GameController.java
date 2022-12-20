package com.training.games.controller;

import com.training.games.controller.dto.CreateGameDto;
import com.training.games.controller.dto.GameDto;
import com.training.games.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class GameController {

    @Autowired
    private GameService gameService;

    @GetMapping("/games")
    public String displayGames(Model model) {
        List<GameDto> gameList = gameService.fetchGames();
        model.addAttribute("games", gameList);
        return "game-list";
    }


    @GetMapping("/games/add")
    public String displayAddGameForm() {
        return "add-game";
    }


    @PostMapping("/games/add")
    public String addGameSubmission(@RequestBody CreateGameDto dto) {
        gameService.addGame(dto);
        return "redirect:/games";
    }

}
