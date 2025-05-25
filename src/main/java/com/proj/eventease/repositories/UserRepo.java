package com.proj.eventease.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proj.eventease.entities.User;

@Repository
public interface UserRepo extends JpaRepository<User,String>{

    Optional<User> findByemail(String email);
    Optional<User> findByemailAndPassword(String email, String password);
}
