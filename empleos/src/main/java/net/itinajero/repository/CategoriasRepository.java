package net.itinajero.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import net.itinajero.modelo.Categoria;


//public interface CategoriasRepository extends CrudRepository<Categoria, Integer> {
////Recordar que la interfaz JpaRepository extiende la interfaz CrudRepository
public interface CategoriasRepository extends JpaRepository<Categoria, Integer> {
}
