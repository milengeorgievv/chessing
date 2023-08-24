package com.diploma.chessing.repository;

import com.diploma.chessing.model.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface OwnerRepository  extends JpaRepository<Owner,Long> {
    @Query("SELECT o FROM Owner o WHERE o.id = ?1")
    Optional<Owner> findOwnerById(Long id);
}
