package com.blue.iotapp.repository;

import com.blue.iotapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Optional;

//Created interface that extends Jpa Repository
@Repository
@CrossOrigin

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
