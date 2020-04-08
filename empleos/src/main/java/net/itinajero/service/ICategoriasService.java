package net.itinajero.service;

import java.util.List;

import net.itinajero.modelo.Categoria;

//INTERFAZ DE SERVICIO. LA CLASE DE SERVICIO Q IMPLEMENTA ESTA INTERFAZ SE UTILIZA EN INYECCION POR DEPENDENCIAS
public interface ICategoriasService {
	List<Categoria>buscarTodas(); //metodo a implementar en clase de servicio
	Categoria buscarPorId(Integer idCategoria);
	void guardar (Categoria categoria);
	
	//Ejercicio implementar metodo
	void eliminar (Categoria idCategoria);

}
