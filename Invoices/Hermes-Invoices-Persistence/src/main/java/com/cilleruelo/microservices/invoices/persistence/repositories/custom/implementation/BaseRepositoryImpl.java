package com.cilleruelo.microservices.invoices.persistence.repositories.custom.implementation;

import java.io.Serializable;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;

import org.apache.commons.lang3.NotImplementedException;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BaseRepositoryImpl<T> {
	
	private static final Logger LOG = LoggerFactory.getLogger(BaseRepositoryImpl.class);
	
	@PersistenceContext
    private EntityManager entityManager;
	
	// Query projection list 
	protected Map<String,String> projectionsList = null;
	
	/**
	 * Metodo que retorna una session de Hibernate para realizar consultas:
	 *    - Nativas
	 *    - JQL
	 *    - Hibernate Criteria
	 *    
	 * @return - Session de Hibernate
	 */
	protected Session getSession(){
		
		return entityManager.unwrap(Session.class);
	}
	
	/**
	 * Method to return a criteria builder
	 */
	protected CriteriaBuilder getCriteriaBuilder(){
	    return entityManager.getCriteriaBuilder();
	}
	
	/**
	 * Method to lazy load a Entity reference from Hibernate session for updates
	 * 
	 * @param clazz Entity to load
	 * @param primaryKey primary Key object
	 */
	@SuppressWarnings("unchecked")
	public Object getEntityReference(@SuppressWarnings("rawtypes") Class clazz, Serializable primaryKey){
		return getSession().load(clazz, primaryKey);
	}
	
	/**
	 * Method to detach Entity from Hibernate session and allowing entity modifications without db changes
	 * 
	 * @param entity Entity to detach
	 */
	void detachEntity(T entity){
		
		getSession().evict(entity);
	}
	
	/**
	 * Builds Entities -> DTO projection fields
	 * 
	 * @return projection map
	 * @throws MissingParameterException
	 */
	protected Map<String, String> getEntityProjections(){
		throw new NotImplementedException("Method not implemented");
		// Extended classes must override it if it's required
	}
	
}
