package com.neo.myhome.repository;

import com.neo.myhome.model.Board;
import com.neo.myhome.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
