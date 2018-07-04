package com.cilleruelo.microservices.authorizationServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * Main Authorization server class
 * URL to get a new token: http://{host}:{port}/oauth/token
 * Basic authorization on header with password and client id
 * Body content (key/value): 
 * - grant_type: password
 * - username: user´s name
 * - password: user´s password
 * The token will be receiven on JSON structure with some addition data (such as the refresh token)
 * For example:
 * {
 *   "access_token": "0e7ab951-7436-4c5b-bfe9-e2f6ab9c8864",
 *   "token_type": "bearer",
 *   "refresh_token": "50ce02cf-46c5-46ba-a7dd-2ae01a022d09",
 *   "expires_in": 25599,
 *   "scope": "read"
 * }
 * 
 * @author francisco.cilleruelo
 *
 */
@SpringBootApplication
@EnableDiscoveryClient // It allows to be registered on the discovery server and gets location info about other components registered on it
@RefreshScope // It allows to refresh the properties have changed on Git by calling the endpoint: http://{host}:{port}/refresh
public class AuthorizationServerApplication {
	
	public static void main( String[] args )
    {
    	SpringApplication.run(AuthorizationServerApplication.class, args);
    }

}