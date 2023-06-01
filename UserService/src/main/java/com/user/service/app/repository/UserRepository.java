package com.user.service.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.user.service.app.entity.User;

public interface UserRepository extends JpaRepository<User, String>{

}
