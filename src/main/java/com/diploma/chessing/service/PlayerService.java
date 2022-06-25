package com.diploma.chessing.service;

import com.diploma.chessing.model.Player;
import com.diploma.chessing.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
public class PlayerService {
    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public List<Player> getPlayers() {
        return playerRepository.findAll();
    }

    public void addNewPlayer(Player player) {
        var playerOptional = playerRepository
                .findPlayerByEmailOrName(player.getEmail(), player.getName());
        if(playerOptional.isPresent()) {
            throw new IllegalStateException("Email or Name taken");
        }
        playerRepository.save(player);
    }

    public void deletePlayer(Long playerId) {
        var exists = playerRepository.existsById(playerId);
        if(!exists) {
            throw new IllegalStateException("Player with id " + playerId + " does not exist");
        }
        playerRepository.deleteById(playerId);
    }

    @Transactional
    public void updatePlayer(Long playerId, String email, String name) {
        var player = playerRepository.findById(playerId)
                .orElseThrow(() -> new IllegalStateException(
                        "Player with id " + playerId + "does not exist"));
        if(email != null &&
            email.length() > 5 &&
            !Objects.equals(player.getEmail(), email)) {
            var playerOptional = playerRepository.findPlayerByEmailOrName(email, null);
            if(playerOptional.isPresent()) {
                throw new IllegalStateException("Email is taken");
            }
            player.setEmail(email);
        }

        if(name != null &&
                name.length() > 5 &&
                !Objects.equals(player.getName(), name)) {
            var playerOptional = playerRepository.findPlayerByEmailOrName(null, email);
            if(playerOptional.isPresent()) {
                throw new IllegalStateException("Name is taken");
            }
            player.setName(name);
        }
    }

}
