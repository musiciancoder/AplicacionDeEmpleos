package net.itinajero.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.itinajero.modelo.Vacante;

//RECORDAR QUE JPAREPOSITORY EXTIENDE DE CRUDREPOSITORY
public interface VacantesRepository extends JpaRepository<Vacante, Integer> {

	//QueryMethod. Con esto queda disponible este metodo para ser llamado por iny. de dependencias.
	List<Vacante>findByEstatus(String estatus);
	//Querymethod keyword And
	List<Vacante>findByDestacadoAndEstatusOrderByIdDesc(int destacado, String estatus);
	//Querymethod keyword Between
	List<Vacante>findBySalarioBetweenOrderBySalario(double s1, double s2);
	//Querymethod keyword In
	List<Vacante>findByEstatusIn(String [] estatus);

}
