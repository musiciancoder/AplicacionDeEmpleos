package net.itinajero.service;

import java.util.List;

import org.springframework.data.domain.Example;

import net.itinajero.modelo.Vacante;

//INTERFAZ DE SERVICIO PARA HACER INYECCION DE DEPENDENCIAS
public interface IVacantesService {
		List<Vacante>buscarTodas(); //metodo a implementar en clase de servicio
		Vacante buscarPorId(Integer idVacante);
		void guardar (Vacante vacante);
		List <Vacante> buscarDestacadas();
		void eliminar (Integer idVacante);
		List<Vacante>buscarByExample(Example<Vacante>example);//metodo query q permite formar consultas tipo select tipo filtros web
}
