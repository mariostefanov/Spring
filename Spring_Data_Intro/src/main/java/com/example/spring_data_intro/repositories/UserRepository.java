package com.example.spring_data_intro.repositories;

import com.example.spring_data_intro.models.Account;
import com.example.spring_data_intro.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User getByUsername(String username);

    Optional<User> findByUsername(String username);
}
