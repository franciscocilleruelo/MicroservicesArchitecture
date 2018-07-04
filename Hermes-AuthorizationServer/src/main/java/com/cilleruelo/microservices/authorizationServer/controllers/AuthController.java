package com.cilleruelo.microservices.authorizationServer.controllers;

import java.security.Principal;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Authentication REST Controller to check the user´s token 
 * and get tha main info (Principal) about him
 * 
 * @author francisco.cilleruelo
 *
 */
@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@RequestMapping(value= "/user", method = RequestMethod.POST)
	public Principal user(Principal user) {
		return user;
	}

}
