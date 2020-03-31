package net.itinajero.service;

import java.util.List;

import net.itinajero.modelo.Categoria;

public interface ICategoriasService {
	List<Categoria>buscarTodas(); //metodo a implementar en clase de servicio
	Categoria buscarPorId(Integer idCategoria);
	void guardar (Categoria categoria);

}
