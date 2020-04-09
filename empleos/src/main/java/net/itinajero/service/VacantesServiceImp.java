package net.itinajero.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import net.itinajero.modelo.Vacante;

//CLASE DE SERVICIO PARA HACER INYECCION POR DEPENDENCIAS
@Service
public class VacantesServiceImp implements IVacantesService {

	private  List<Vacante> lista = null; //declaramos e inicializamos con valor nulo
	
	 public VacantesServiceImp (){ //constructor
	

		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		lista=new LinkedList<Vacante>();
		
		
		try {
			
			Vacante vacante1= new Vacante();
			vacante1.setId(1);
			vacante1.setNombre("Medico");
			vacante1.setDescripcion("Internista");
			vacante1.setFecha(sdf.parse("02-03-2020"));
			vacante1.setSalario(14000.0);
			vacante1.setDestacado(1);
			vacante1.setImagen("logo1.png");
			
			
			Vacante vacante2= new Vacante();
			vacante2.setId(2);
			vacante2.setNombre("Ingeniero");
			vacante2.setDescripcion("Junior");
			vacante2.setFecha(sdf.parse("01-04-2020"));
			vacante2.setSalario(10000.0);
			vacante2.setDestacado(0);
			vacante2.setImagen("logo2.png");
			
			Vacante vacante3= new Vacante();
			vacante3.setId(3);
			vacante3.setNombre("Administrativo");
			vacante3.setDescripcion("Ayudante");
			vacante3.setFecha(sdf.parse("04-04-2020"));
			vacante3.setSalario(2000.0);
			vacante3.setDestacado(0);
			vacante3.setImagen("logo3.png");
			
			Vacante vacante4= new Vacante();
			vacante4.setId(4);
			vacante4.setNombre("Operario");
			vacante4.setDescripcion("Albañil");
			vacante4.setFecha(sdf.parse("04-05-2020"));
			vacante4.setSalario(1590.0);
			vacante4.setDestacado(1);
			vacante4.setImagen("logo4.png");
			
			lista.add(vacante1);
			lista.add(vacante2);
			lista.add(vacante3);
			lista.add(vacante4);
			
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		
	}
	 
	 public List<Vacante>buscarTodas(){
		 return lista;
	 }

	@Override
	public Vacante buscarPorId(Integer idVacante) {
		
		for (Vacante v: lista) {
			if (v.getId()==idVacante) {
				return v; //devuelve esa vacante
			}
		}
		
		return null;
	}

	@Override
	public void guardar(Vacante vacante) {
		lista.add(vacante);
		
	}

	@Override
	public List<Vacante> buscarDestacadas() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void eliminar(Integer integer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Vacante> buscarByExample(Example<Vacante> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Vacante> buscarTodas(Pageable page) {
		// TODO Auto-generated method stub
		return null;
	}

}
