package com.stb.sample.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stb.sample.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
