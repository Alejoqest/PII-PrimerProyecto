package com.cooweb.PrimerProyecto.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cooweb.PrimerProyecto.dao.UsuarioDao;
import com.cooweb.PrimerProyecto.models.Usuario;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;


@RestController
public class UsuarioController {
	
	@Autowired
	private UsuarioDao dao;
	
	@GetMapping("api/usuarios")
	public List<Usuario> getUsuarios() {
		return dao.getUsuarios();
	}
	
	@PostMapping("api/usuarios")
	public void registrarUsuario(@RequestBody Usuario usuario) {
		
		Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
		
		usuario.setPassword(
				argon2.hash(1, 1024, 1, usuario.getPassword().getBytes())
				);
		
		dao.registrar(usuario);
	}
	
	@DeleteMapping("api/usuarios/{id}")
	public void deleteUsuario(@PathVariable Long id) {
		dao.eliminar(id);
	}
	
	
	/*@RequestMapping(value = "mensaje")
	public String mensaje() {
		return "hola";
	}
	
	@RequestMapping(value = "persona")
	public List<String> listarPersonas() {
		return List.of("Diego", "Juan", "Jose");
	}
	
	@RequestMapping(value = "usuarios")
	public Usuario listarUsuarios() {
		
		Usuario usuario = new Usuario();
		
		usuario.setNombre("Diego");
		usuario.setApellido("Vargas");
		usuario.setEmail("dvargasgodoy@gmail.com");
		usuario.setTelefono("155619965");
			
		return usuario;
	}
	
	@RequestMapping(value = "usuario/{id}")
	public Usuario getUsuario(@PathVariable Long id) {
		
		Usuario usuario = new Usuario();
		
		usuario.setId(id);
		usuario.setNombre("Diego");
		usuario.setApellido("Vargas");
		usuario.setEmail("dvargasgodoy@gmail.com");
		usuario.setTelefono("155619965");
			
		return usuario;
	}
	
	@RequestMapping(value = "listar/usuarios")
	public List<Usuario> listar_variosUsuarios() {
		
		List<Usuario> usuarios = new ArrayList<>();
		
		Usuario usuario1 = new Usuario();
		
		usuario1.setId(3L);
		usuario1.setNombre("Diego");
		usuario1.setApellido("Vargas");
		usuario1.setEmail("dvargasgodoy@gmail.com");
		usuario1.setTelefono("155619965");
		
		Usuario usuario2 = new Usuario();
		
		usuario2.setId(4L);
		usuario2.setNombre("Jose");
		usuario2.setApellido("Puntos");
		usuario2.setEmail("jpseeeey@gmail.com");
		usuario2.setTelefono("1556555565");
		
		usuarios.add(usuario1);
		usuarios.add(usuario2);
		
		return usuarios;
	}*/
	
	
}
