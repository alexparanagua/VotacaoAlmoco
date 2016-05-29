package com.xtest.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.xtest.models.Usuario;

@Transactional
public class UsuarioDaoImpl {
	
	@PersistenceContext
	private EntityManager em;
	
	public int save(Usuario uu) {
		em.persist(uu);
		return uu.getId();
	}
	
	public void delete(Usuario uu){
		em.remove(em.contains(uu) ? uu : em.merge(uu));
	}
	
	public List<Usuario> getAll(){
		return em.createQuery("SELECT u FROM Usuario u",Usuario.class).getResultList();
	}
	
	public Usuario getLogon(String email,String pw){
		try{
			return em.createQuery("SELECT u FROM Usuario u WHERE u.email='"+email+"' and u.senha='"+pw+"'",Usuario.class).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
}
