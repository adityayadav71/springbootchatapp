package com.aditya.chatapp.rest;

import org.springframework.web.bind.annotation.RestController;

import com.aditya.chatapp.entities.UserRoleMapping;
import com.aditya.chatapp.entities.User;
import com.aditya.chatapp.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class UserController {

    private final UserService userService;
    private final ObjectMapper objectMapper;

    UserController(UserService userService, ObjectMapper objectMapper) {
        this.userService = userService;
        this.objectMapper = objectMapper;
    }

	@GetMapping("/api/users")
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}
	
	@GetMapping("/api/users/{userId}")
	public User getUser(@PathVariable int userId) {
		User user = userService.getUserById(userId);
		
		if(user == null) {
			throw new RuntimeException("User not found - " + userId);
		}
		
		return user;
	}
	
	@GetMapping("/api/users/{userId}/roles")
	public List<UserRoleMapping> getAllRoles(@PathVariable int userId) {
		return userService.getAllRoles(userId);
	}
	
	@PostMapping("/api/users")
	public User createUser(@RequestBody User user) {
		return userService.createNewUser(user);
	}
	
	@PutMapping("/api/users")
	public User updateUser(@RequestBody User user) {
		return userService.updateUser(user);
	}
	
	@DeleteMapping("/api/users/{userId}")
	public void updateUser(@PathVariable int userId) {
		userService.deleteUser(userId);
	}
	
	@PatchMapping("/api/users/{userId}")
	public User updateUser(@PathVariable int userId, @RequestBody Map<String, Object> patchPayload) {
		User originalUser = userService.getUserById(userId);
		
		if(originalUser == null) {
			throw new RuntimeException("User not found - " + userId);
		}
		
		if(patchPayload.containsKey("id")) {
			throw new RuntimeException("User id is not allowed in request body - " + userId);
		}
		
		User patchedUser = applyPatch(patchPayload, originalUser);
		
		User dbUser = userService.updateUser(patchedUser);
		
		return dbUser;
	}
	
	public User applyPatch(Map<String, Object> patchPayload, User originalUser) { 
		ObjectNode userNode = objectMapper.convertValue(originalUser, ObjectNode.class);
		
		ObjectNode patchNode = objectMapper.convertValue(patchPayload, ObjectNode.class);
		
		userNode.setAll(patchNode);
		
		return objectMapper.convertValue(userNode, User.class);
	}
}
