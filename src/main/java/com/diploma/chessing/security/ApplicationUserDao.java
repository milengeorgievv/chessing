package com.diploma.chessing.security;

import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface ApplicationUserDao {
    Optional<ApplicationUser> selectApplicationUserByUsername(String username);
}
