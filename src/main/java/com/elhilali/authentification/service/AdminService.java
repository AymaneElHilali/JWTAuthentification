package com.elhilali.authentification.service;

import com.elhilali.authentification.dataAcces.Admin;
import com.elhilali.authentification.dataAcces.AdminRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    AdminRepo adminRepo;
    public Admin signup(Admin admin){
        return adminRepo.save(admin);

    }
    public Admin findByEmail(String email) {
        return adminRepo.findByEmail(email);
    }
}
