package com.cilleruelo.microservices.authorizationServer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cilleruelo.microservices.authorizationServer.beans.BaseUserDTO;
import com.cilleruelo.microservices.authorizationServer.beans.UserDTO;
import com.cilleruelo.microservices.authorizationServer.facade.UserFacade;

/**
 * REST Controller to manage the authenticated users
 * 
 * @author francisco.cilleruelo
 *
 */
@RestController
@RequestMapping("/users")
public class UserRestController {
	
	@Autowired
	private UserFacade userFacade;
	
	/**
	 * Get user information
	 * 
	 * @param username
	 * @return
	 */
	@RequestMapping(value = "/{username}", method = RequestMethod.GET)
	public BaseUserDTO user(@PathVariable String username) {
		return userFacade.getUser(username);
	}

	/**
	 * Add a new user
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public BaseUserDTO user(@RequestBody BaseUserDTO user) {
		return userFacade.addUser(user);
	}
	
	/**
	 * Update an existing user
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(method = RequestMethod.PUT)
	public UserDTO user(@RequestBody UserDTO user) {
		return userFacade.editUser(user);
	}
	
	/**
	 * Edit the passsword for an existing user
	 * 
	 * @param username
	 * @param user User with new password
	 * @return
	 */
	@RequestMapping(value = "/password/{username}", method = RequestMethod.POST)
	public BaseUserDTO editPassword(@PathVariable String username, @RequestBody BaseUserDTO user) {
		return userFacade.editUserPassword(user);
	}
}
