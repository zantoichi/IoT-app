package com.blue.iotapp.repository;

import com.blue.iotapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

//Created interface that extends Jpa Repository
@Repository
@CrossOrigin
//TODO: RETURN OPTIONAL WHERE POSSIBLE
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
