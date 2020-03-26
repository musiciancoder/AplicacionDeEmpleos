package net.itinajero.service;

import java.util.List;

import net.itinajero.modelo.Vacante;

//INTERFAZ DE SERVICIO PARA HACER INYECCION DE DEPENDENCIAS
public interface IVacantesService {
		List<Vacante>buscarTodas(); //metodo a implementar en clase de servicio
}
