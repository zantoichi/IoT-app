package com.blue.iotapp.repository;

import com.blue.iotapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//Created interface that extends Jpa Repository
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
