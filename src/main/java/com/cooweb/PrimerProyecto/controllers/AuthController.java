package com.cooweb.PrimerProyecto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cooweb.PrimerProyecto.dao.UsuarioDao;
import com.cooweb.PrimerProyecto.models.Usuario;

@RestController
public class AuthController {

	@Autowired
	private UsuarioDao dao;
	
	@PostMapping("api/login")
	public String login(@RequestBody Usuario usuario) {
		
		if (dao.verificarCredenciales(usuario)) {
			return "Ok";
		}
		return "Fail";
	}
}
