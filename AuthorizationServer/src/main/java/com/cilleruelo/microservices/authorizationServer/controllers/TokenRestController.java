package com.cilleruelo.microservices.authorizationServer.controllers;

import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller to manager the token
 * 
 * @author francisco.cilleruelo
 *
 */
@RestController
@RequestMapping("/tokens")
public class TokenRestController {
	
	private ConsumerTokenServices tokenServices;
	     
	/**
	 * Revoke a token by its id
	 * 
	 * @param tokenId
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/revoke/{tokenId:.*}")
	public String revokeToken(@PathVariable String tokenId) {
	    tokenServices.revokeToken(tokenId);
	    return tokenId;
	}
}
