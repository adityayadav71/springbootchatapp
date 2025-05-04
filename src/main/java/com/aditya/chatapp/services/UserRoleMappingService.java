package com.aditya.chatapp.services;

import java.util.List;
import com.aditya.chatapp.repositories.UserRoleMappingRepository;
import org.springframework.stereotype.Service;

import com.aditya.chatapp.entities.UserRoleMapping;

@Service
public class UserRoleMappingService {

    private final UserRoleMappingRepository userRoleMappingRepository;

    UserRoleMappingService(UserRoleMappingRepository userRoleMappingRepository) {
        this.userRoleMappingRepository = userRoleMappingRepository;
    }
	
	public List<UserRoleMapping> getAllUserRoleMappings() {
		return userRoleMappingRepository.findAll();
	}
	
	public UserRoleMapping createUserRoleMapping(UserRoleMapping userRole) {
		UserRoleMapping newUserRoleMapping = userRoleMappingRepository.save(userRole);
		
		return newUserRoleMapping;
	}
	
	public void deleteUserRoleMapping(UserRoleMapping userRole) {
		userRoleMappingRepository.delete(userRole);
	}
}
