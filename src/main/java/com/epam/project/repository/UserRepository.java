package com.epam.project.repository;

import com.epam.project.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findUserByLogin(String login);
    User findByLogin(String login);

    User findUserById(UUID id);

    boolean existsById(UUID id);

    @Query(value = "SELECT u.firstName FROM User u")
    List<User> findUserByFirstName();
}
