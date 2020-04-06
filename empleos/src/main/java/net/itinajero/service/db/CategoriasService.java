package net.itinajero.service.db;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.itinajero.modelo.Categoria;
import net.itinajero.repository.CategoriasRepository;
import net.itinajero.service.ICategoriasService;

//CLASE DE SERVICIO PARA HACER INY POR DEPENDENCIAS EN EL CONTROLADOR
@Service
public class CategoriasService implements ICategoriasService{

	//Ejecucion de Iny de dependencias de repositorio
	@Autowired
	private CategoriasRepository categoriasRepo;
	
	@Override
	public List<Categoria> buscarTodas() {
		
		return categoriasRepo.findAll(); 
	}

	@Override
	public Categoria buscarPorId(Integer idCategoria) {
		Optional <Categoria> optional= categoriasRepo.findById(idCategoria);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public void guardar(Categoria categoria) {
		categoriasRepo.save(categoria);
		
	}

}
