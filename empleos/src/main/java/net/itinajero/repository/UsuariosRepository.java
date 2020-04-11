package net.itinajero.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.itinajero.modelo.Usuario;

public interface UsuariosRepository extends JpaRepository<Usuario, Integer> {
	
	Usuario findByUsername(String username);

}
