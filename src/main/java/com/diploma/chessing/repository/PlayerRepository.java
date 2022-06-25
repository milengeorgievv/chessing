package com.diploma.chessing.repository;

import com.diploma.chessing.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
    @Query("SELECT p FROM Player p WHERE p.email = ?1 OR p.name = ?2")
    Optional<Player> findPlayerByEmailOrName(String email, String name);
}
