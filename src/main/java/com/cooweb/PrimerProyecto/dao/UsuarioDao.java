package com.cooweb.PrimerProyecto.dao;

import java.util.List;

import com.cooweb.PrimerProyecto.models.Usuario;

import jakarta.transaction.Transactional;

@Transactional
public interface UsuarioDao {

	List<Usuario> getUsuarios();
}
