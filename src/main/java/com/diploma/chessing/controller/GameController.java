package com.diploma.chessing.controller;

import com.diploma.chessing.enumeration.Piece;
import com.diploma.chessing.enumeration.Square;
import com.diploma.chessing.model.Game;
import com.diploma.chessing.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "api/v1/game")
public class GameController {
    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping
    public List<Game> getGames() {
        return gameService.getGames();
    }

    @PostMapping
    public void createGame(@RequestBody Game game) {
        gameService.createGame(game);
    }

    @DeleteMapping(path = "{gameId}")
    public void deleteGame(@PathVariable("gameId") Long gameId) {
        gameService.deleteGame(gameId);
    }

    @PutMapping(path = "{gameId}")
    public void startGame(
            @PathVariable("gameId") Long gameId) {
        gameService.startGame(gameId);
    }

    @PutMapping(path = "{gameId}")
    public void finishGame(
            @PathVariable("gameId") Long gameId,
            @RequestParam boolean hasWinner,
            @RequestParam Long winnerId) {
        gameService.finishGame(gameId, hasWinner, winnerId);
    }

    @PutMapping(path = "{gameId}")
    public void updateMoveInGame(
            @PathVariable("gameId") Long gameId,
            @RequestParam LocalTime timeLeft,
            @RequestParam Map<Square, Piece> newPosition) {
        gameService.updateMoveInGame(gameId, timeLeft, newPosition);
    }
}
