package com.epam.project.repository;

import com.epam.project.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByUserLoginName(String userLoginName);

    @Query("select u.id from User u where u.id=:id")
    UUID findUserById(UUID id);

    boolean existsById(UUID id);
}
