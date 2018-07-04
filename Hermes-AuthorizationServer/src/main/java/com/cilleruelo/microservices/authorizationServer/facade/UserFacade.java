package com.cilleruelo.microservices.authorizationServer.facade;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.cilleruelo.microservices.authorizationServer.beans.BaseUserDTO;
import com.cilleruelo.microservices.authorizationServer.beans.UserDTO;

@Service
public interface UserFacade extends UserDetailsService {
	
	/**
	 * Add a new user
	 * 
	 * @param user
	 * @return
	 */
	public BaseUserDTO addUser(BaseUserDTO user);
	
	
	/**
	 * Edit an existing user
	 * 
	 * @param user
	 * @return
	 */
	public UserDTO editUser(UserDTO user);
	
	
	/**
	 * Get user details about an existing user
	 * 
	 * @param username
	 * @return
	 */
	public BaseUserDTO getUser(String username);
	
	
	/**
	 * Edit the password for an existing user
	 * 
	 * @param user
	 * @return
	 */
	public BaseUserDTO editUserPassword(BaseUserDTO user);

}
