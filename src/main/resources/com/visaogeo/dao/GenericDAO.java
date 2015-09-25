package com.visaogeo.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.visaogeo.util.JpaUtil;

public class GenericDAO<T> {
	
	protected EntityManager em = JpaUtil.getEntityManager();
	
	public List<T> buscarTodos(String classe){
		return em.createQuery("select x from " +classe+" x ").getResultList();
	}
	
	public void salvar(T entity){
		em.getTransaction().begin();
		try{
			em.persist(entity);
			em.getTransaction().commit();
		}catch(Exception e){
			em.getTransaction().rollback();
			e.printStackTrace();
		}
	}
}
