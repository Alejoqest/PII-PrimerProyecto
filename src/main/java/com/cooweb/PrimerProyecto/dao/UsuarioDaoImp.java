package com.cooweb.PrimerProyecto.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cooweb.PrimerProyecto.models.Usuario;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class UsuarioDaoImp implements UsuarioDao {
	
	@PersistenceContext
	private EntityManager eM;
	
	@Override
	public List<Usuario> getUsuarios() {
		String query="from Usuario";
		return eM.createQuery(query).getResultList();
	}
}
