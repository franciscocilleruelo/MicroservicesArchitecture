package com.cilleruelo.microservices.authorizationServer.facade.impl;

import javax.transaction.Transactional;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.cilleruelo.microservices.authorizationServer.beans.BaseUserDTO;
import com.cilleruelo.microservices.authorizationServer.beans.UserDTO;
import com.cilleruelo.microservices.authorizationServer.facade.UserFacade;
import com.cilleruelo.microservices.authorizationServer.persistence.entities.Users;
import com.cilleruelo.microservices.authorizationServer.persistence.entities.pk.UsersPK;
import com.cilleruelo.microservices.authorizationServer.persistence.repositories.UsersRepository;

@Service
@Transactional
public class UserFacadeImpl implements UserFacade {

	private static final Logger log = LoggerFactory.getLogger(UserFacadeImpl.class);

	@Autowired
	private UsersRepository usersRepositoy;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
			UsersPK usersPK = new UsersPK(username.toUpperCase().trim());
			Users userJpa = usersRepositoy.findOne(usersPK);
			if (userJpa != null) {
				UserDTO userDto = new UserDTO();
				try {
					BeanUtils.copyProperties(userDto, userJpa);
				} catch (Exception ex) {
					log.error("Error copiando usuario de base de datos al usuario de autenticacion :", ex);
				}
				return userDto;
			} else {
				throw new UsernameNotFoundException("Usuario no encontrado");
			}
		} catch (Exception e) {
			log.error("Error en la autenticación del usuario: ", e);
		}
		return null;
	}
	
	@Override
	public BaseUserDTO addUser(BaseUserDTO user){
		if (StringUtils.hasText(user.getUsername())){
			UsersPK usersPk = new UsersPK(user.getUsername().toUpperCase().trim());
			if (usersRepositoy.findOne(usersPk)==null){
				Users userJpa = new Users();
				userJpa.setUsername(user.getUsername().toUpperCase());
				String passEncoded = new BCryptPasswordEncoder().encode(user.getPassword());
				userJpa.setPassword(passEncoded);
				userJpa.setAccountNonExpired(true);
				userJpa.setAccountNonlocked(true);
				userJpa.setCredentialsNonExpired(true);
				userJpa.setEnabled(true);
				usersRepositoy.save(userJpa);
			}
		}
		
		return user;
	}
	
	@Override
	public UserDTO editUser(UserDTO user){
		if (StringUtils.hasText(user.getUsername())){
			UsersPK usersPk = new UsersPK(user.getUsername().toUpperCase().trim());
			Users userJpa = usersRepositoy.findOne(usersPk);
			if (userJpa!=null){
				try {
					BeanUtils.copyProperties(user, userJpa);
				} catch (Exception ex) {
					log.error("Error copiando usuario de base de datos al usuario de autenticacion :", ex);
				}
				usersRepositoy.save(userJpa);
			} else {
				throw new UsernameNotFoundException("Usuario no encontrado");
			}
		}
		
		return user;
	}
	
	@Override
	public BaseUserDTO getUser(String username){
		if (StringUtils.hasText(username)){
			UsersPK usersPk = new UsersPK(username.toUpperCase().trim());
			Users userJpa = usersRepositoy.findOne(usersPk);
			if (userJpa!=null){
				UserDTO user = new UserDTO();
				try {
					BeanUtils.copyProperties(user, userJpa);
				} catch (Exception ex) {
					log.error("Error copiando usuario de base de datos al usuario de autenticacion :", ex);
				}
				return user;
			} else {
				return null;
			}
		} else {
			return null;
		}
	}
	
	@Override
	public BaseUserDTO editUserPassword(BaseUserDTO user){
		if (StringUtils.hasText(user.getUsername())){
			UsersPK usersPk = new UsersPK(user.getUsername().toUpperCase().trim());
			Users userJpa = usersRepositoy.findOne(usersPk);
			if (userJpa!=null){
				try {
					String passEncoded = new BCryptPasswordEncoder().encode(user.getPassword());
					userJpa.setPassword(passEncoded);
					usersRepositoy.save(userJpa);
				} catch (Exception ex) {
					log.error("Error modificando la clave de autenticacion del usuario {} :", user.getUsername(), ex);
				}				
			} else {
				throw new UsernameNotFoundException("Usuario no encontrado");
			}
		}
		
		return user;
	}

}
