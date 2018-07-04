package com.cilleruelo.microservices.authorizationServer.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cilleruelo.microservices.authorizationServer.persistence.entities.Users;
import com.cilleruelo.microservices.authorizationServer.persistence.entities.pk.UsersPK;

/**
 * JPA Repository for users
 * 
 * @author francisco.cilleruelo
 *
 */
public interface UsersRepository extends JpaRepository<Users, UsersPK> {

}
