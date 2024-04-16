package com.javamentor.springboot312.repository;

import com.javamentor.springboot312.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
}
