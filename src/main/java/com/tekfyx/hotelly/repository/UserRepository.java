package com.tekfyx.hotelly.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tekfyx.hotelly.entity.User;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
