package com.aditya.chatapp.rest;

import java.util.List;
import com.aditya.chatapp.services.UserRoleMappingService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.aditya.chatapp.entities.UserRoleMapping;

@RestController
public class UserRoleMappingController {

    private final UserRoleMappingService userRoleMappingService;

    UserRoleMappingController(UserRoleMappingService userRoleMappingService) {
        this.userRoleMappingService = userRoleMappingService;
    }
	
	@GetMapping("/api/roleMappings")
	public List<UserRoleMapping> getAllUserRoleMappings() {
		return userRoleMappingService.getAllUserRoleMappings();
	}
	
	@PostMapping("/api/roleMappings")
	public UserRoleMapping createUserRole(@RequestBody UserRoleMapping userRole) {
		return userRoleMappingService.createUserRoleMapping(userRole);
	}
	
	@DeleteMapping("/api/roleMappings")
	public void deleteUserRole(@RequestBody UserRoleMapping userRole) {
		userRoleMappingService.deleteUserRoleMapping(userRole);
	}
}
