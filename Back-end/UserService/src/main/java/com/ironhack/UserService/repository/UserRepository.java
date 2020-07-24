package com.ironhack.UserService.repository;

import com.ironhack.UserService.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
        Optional<User> findByUsername(String username);
}
