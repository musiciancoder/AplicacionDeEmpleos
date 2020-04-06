package net.itinajero.service.db;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import net.itinajero.modelo.Vacante;
import net.itinajero.repository.VacantesRepository;
import net.itinajero.service.IVacantesService;

@Service
@Primary
public class VacantesServiceJpa implements IVacantesService {

	@Autowired
	private VacantesRepository vacantesRepo;
	
	@Override
	public List<Vacante> buscarTodas() {
		return vacantesRepo.findAll();
		
	}

	@Override
	public Vacante buscarPorId(Integer idVacante) {
	Optional <Vacante> vacante = vacantesRepo.findById(idVacante);
		if (vacante.isPresent()) {
		return	vacante.get();
			
		}
			return null;
		}
	

	@Override
	public void guardar(Vacante vacante) {
		vacantesRepo.save(vacante);

	}

}
