package com.example.Authorization.Repository;

import com.example.Authorization.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String>{
	User findByUserEmailAndUserPassword(String userEmail, String password);
}

