package com.cooweb.PrimerProyecto.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cooweb.PrimerProyecto.models.Usuario;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
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
		String query = "FROM Usuario";
		return eM.createQuery(query).getResultList();
	}

	@Override
	public void eliminar(Long id) {
		Usuario user = eM.find(Usuario.class, id);
		
		eM.remove(user);
	}

	@Override
	public void registrar(Usuario usuario) {
		eM.merge(usuario);
	}

	@Override
	public boolean verificarCredenciales(Usuario usuario) {
		// TODO Auto-generated method stub
		String query = "FROM Usuario WHERE email = :email";
		
		List<Usuario> lista = eM.createQuery(query)
				.setParameter("email", usuario.getEmail())
				.getResultList();
		
		if (lista.isEmpty()) {
			return false;
		}
		
		String password = lista.get(0).getPassword();
		
		Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
		
		return (argon2.verify(password, usuario.getPassword().getBytes())); 
	}
}
