package com.training.games.controller.api;

import com.training.games.controller.dto.CreateGameDto;
import com.training.games.controller.dto.GameDto;
import com.training.games.service.GameService;
import com.training.games.service.exception.GameNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/api")
public class GameRestController {

    @Autowired
    private GameService gameService;

    @GetMapping("/games")
    public ResponseEntity<List<GameDto>> fetchGames() {
        List<GameDto> gameList = gameService.fetchGames();
        return ResponseEntity.status(HttpStatus.OK).body(gameList);
    }

    @GetMapping("/games/{id}")
    public ResponseEntity<GameDto> getGamesById(@PathVariable("id") Long gameId) {
        try {
            GameDto gameDto = gameService.fetchGameById(gameId);
            return ResponseEntity.status(HttpStatus.OK).body(gameDto);

        } catch (GameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/games")
    public ResponseEntity<GameDto> addGameSubmission(@RequestBody CreateGameDto dto) {
        GameDto gameDto = gameService.addGame(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(gameDto);
    }


    @PutMapping("/games/{id}")
    public ResponseEntity<GameDto> updateGame(@PathVariable("id") Long idGame, @RequestBody GameDto dto) {
        try {
            GameDto updatedGame = gameService.updateGame(idGame, dto);
            return ResponseEntity.status(HttpStatus.OK).body(updatedGame);
        } catch (GameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/games/{id}")
    public ResponseEntity<HttpStatus> deleteGameById(@PathVariable("id") Long idGame) {
        try {
            gameService.deleteGame(idGame);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (GameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


}
