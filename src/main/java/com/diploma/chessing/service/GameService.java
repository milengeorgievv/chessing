package com.diploma.chessing.service;

import com.diploma.chessing.enumeration.Piece;
import com.diploma.chessing.enumeration.Square;
import com.diploma.chessing.model.Game;
import com.diploma.chessing.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

@Service
public class GameService {
    private final GameRepository gameRepository;

    @Autowired
    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public List<Game> getGames() {
        return gameRepository.findAll();
    }

    public void createGame(Game game) {
        gameRepository.save(game);
    }

    public void deleteGame(Long gameId) {
        var exists = gameRepository.existsById(gameId);
        if(!exists) {
            throw new IllegalStateException("Game with id " + gameId + "does not exist");
        }
        gameRepository.deleteById(gameId);
    }

    @Transactional
    public void startGame(Long gameId) {
        var game = gameRepository.findById(gameId)
                .orElseThrow(() -> new IllegalStateException(
                        "Game with id " + gameId + "does not exist"));
        game.setHasStarted(true);
    }

    @Transactional
    public void finishGame(Long gameId, boolean hasWinner, Long winnerId) {
        var game = gameRepository.findById(gameId)
                .orElseThrow(() -> new IllegalStateException(
                        "Game with id " + gameId + "does not exist"));
        game.setHasFinished(true);
        game.setHasWinner(hasWinner);
        if(hasWinner) {
            game.setWinnerID(winnerId);
        }
    }

    @Transactional
    public void updateMoveInGame(Long gameId, LocalTime timeLeft, Map<Square, Piece> newPosition) {
        var game = gameRepository.findById(gameId)
                .orElseThrow(() -> new IllegalStateException(
                        "Game with id " + gameId + "does not exist"));
        game.setPosition(newPosition);
        game.setWhiteToMove(!game.isWhiteToMove());
        if(game.isWhiteToMove()) {
            game.setWhiteTimeLeft(timeLeft);
        } else {
            game.setBlackTimeLeft(timeLeft);
        }
    }

}
