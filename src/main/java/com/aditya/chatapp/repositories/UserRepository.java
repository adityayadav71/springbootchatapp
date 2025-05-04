package com.aditya.chatapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aditya.chatapp.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
