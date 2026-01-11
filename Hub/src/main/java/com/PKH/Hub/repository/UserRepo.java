package com.PKH.Hub.repository;

import com.PKH.Hub.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {
    boolean existsByUsername(String username);
    boolean existsByEmail(String Email);

    Optional<User> findByEmail(String Email);
}
