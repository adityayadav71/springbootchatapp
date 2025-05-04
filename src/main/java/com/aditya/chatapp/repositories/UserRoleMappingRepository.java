package com.aditya.chatapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aditya.chatapp.entities.UserRoleMapping;
import com.aditya.chatapp.entities.User;

public interface UserRoleMappingRepository extends JpaRepository<UserRoleMapping, Integer> {
	public List<UserRoleMapping> findAllByUser(User user);
}
