package com.aditya.chatapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.aditya.chatapp.repositories.UserRoleMappingRepository;
import com.aditya.chatapp.repositories.UserRepository;
import com.aditya.chatapp.utils.Enums;


import com.aditya.chatapp.entities.UserRoleMapping;
import com.aditya.chatapp.entities.User;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserRoleMappingRepository userRoleMappingRepository;

    UserService(UserRepository userRepository, UserRoleMappingRepository userRoleMappingRepository) {
        this.userRepository = userRepository;
        this.userRoleMappingRepository = userRoleMappingRepository;
    }
	
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}
	
	public User getUserById(int userId) {
		Optional<User> userOptional = userRepository.findById(userId);
		
		if(userOptional.isPresent()) return userOptional.get();
		else return null;
	}
	
	public List<UserRoleMapping> getAllRoles(int userId) {
		Optional<User> userOptional = userRepository.findById(userId);
		
		if(userOptional.isPresent()) {
			List<UserRoleMapping> roleMapping = userRoleMappingRepository.findAllByUser(userOptional.get());
			
			return roleMapping;
		} else {
			throw new RuntimeException("User not found - " + userId);
		}		 
	}
	
	public User createNewUser(User user) {
		User newUser = userRepository.save(user);
		
		// Create default user role for the new user		
		UserRoleMapping userRole = new UserRoleMapping(newUser, Enums.UserRole.USER);
		userRoleMappingRepository.save(userRole);
		
		return newUser;
	}
	
	public User updateUser(User user) {
		User updatedUser = userRepository.save(user);
		
		return updatedUser;
	}
	
	public void deleteUser(int userId) {
		userRepository.deleteById(userId);
	}
}
