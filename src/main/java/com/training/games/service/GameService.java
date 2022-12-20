package com.training.games.service;

import com.training.games.controller.dto.CreateGameDto;
import com.training.games.controller.dto.GameDto;
import com.training.games.repository.GameRepository;
import com.training.games.repository.entity.Game;
import com.training.games.service.exception.GameNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    public List<GameDto> fetchGames() {
        List<Game> gameList = (List<Game>) gameRepository.findAll();
        return gameList.stream()
                .map(g -> GameDto.from(g))
                .collect(Collectors.toList());
    }

    public GameDto fetchGameById(Long gameId) throws GameNotFoundException {
        return gameRepository
                .findById(gameId)
                .map(g -> GameDto.from(g))
                .orElseThrow(() -> new GameNotFoundException(gameId));
    }

    public GameDto addGame(CreateGameDto dto) {
        Game game = new Game(dto.getName(), dto.getDescription());
        // Generate created Date when saving game
        game.setCreatedAt(LocalDate.now());
        gameRepository.save(game);
        return GameDto.from(game);
    }


    public GameDto updateGame(Long idGame, GameDto dto) throws GameNotFoundException {
        Optional<Game> game = gameRepository.findById(idGame);
        if (game.isEmpty()) {
            throw new GameNotFoundException(idGame);
        }
        Game gameUpdate = game.get();
        gameUpdate.setName(dto.getName());
        gameUpdate.setDescription(dto.getDescription());
        gameUpdate.setCreatedAt(dto.getCreatedAt());

        Game gameUpdated = gameRepository.save(gameUpdate);
        return GameDto.from(gameUpdated);
    }

    public void deleteGame(Long idGame) throws GameNotFoundException {
        Optional<Game> game = gameRepository.findById(idGame);
        if (game.isEmpty()) {
            throw new GameNotFoundException(idGame);
        }
        gameRepository.deleteById(idGame);
    }
}
