package com.aditya.chatapp.security;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	
	@Bean
	public UserDetailsManager userDetailsManager(DataSource dataSource) {
		JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
		
		jdbcUserDetailsManager.setUsersByUsernameQuery("select id, username, password, is_active from users where id=?");
		
		jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("select user_id, role from user_role_mappings where user_id=?");
		
		return jdbcUserDetailsManager;
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http.authorizeHttpRequests(configurer -> 
			configurer.requestMatchers(HttpMethod.POST, "/api/users").hasRole("ADMIN")
			.requestMatchers(HttpMethod.PUT, "/api/users").hasAnyRole("MANAGER", "ADMIN")
			.requestMatchers(HttpMethod.PATCH, "/api/users").hasAnyRole("MANAGER", "ADMIN")
			.requestMatchers(HttpMethod.DELETE, "/api/users").hasRole("ADMIN")
			.requestMatchers(HttpMethod.GET, "/api/roleMappings").hasRole("USER")
			.requestMatchers(HttpMethod.GET, "/api/roleMappings/**").hasAnyRole("MANAGER", "ADMIN")
			.requestMatchers(HttpMethod.POST, "/api/roleMappings").hasAnyRole("MANAGER", "ADMIN")
			.requestMatchers(HttpMethod.DELETE, "/api/roleMappings").hasRole("ADMIN")
		);
		
		http.httpBasic(Customizer.withDefaults());
		
		return http.build();
	}
}
