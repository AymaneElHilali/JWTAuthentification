package com.elhilali.authentification.dataAcces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepo extends JpaRepository<Admin,Long> {

    Admin findByEmail(String email);
}
