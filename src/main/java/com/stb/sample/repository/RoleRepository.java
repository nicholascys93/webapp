package com.stb.sample.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stb.sample.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
}
