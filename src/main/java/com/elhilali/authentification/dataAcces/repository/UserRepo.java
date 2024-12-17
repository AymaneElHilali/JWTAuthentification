package com.elhilali.authentification.dataAcces.repository;

import com.elhilali.authentification.dataAcces.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {

    User findByEmail(String email);
}
