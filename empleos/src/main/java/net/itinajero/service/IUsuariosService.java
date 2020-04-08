package net.itinajero.service;

import java.util.List;

import net.itinajero.modelo.Usuario;

public interface IUsuariosService {
	
	void guardar(Usuario usuario);
	
	void eliminar(Integer idUsuario);
	
	List<Usuario>buscarTodos();

}
