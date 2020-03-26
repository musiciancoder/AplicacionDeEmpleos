package net.itinajero.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import net.itinajero.modelo.Vacante;

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
			vacante1.setSalario(12000.0);
			vacante1.setDe(1);
			vacante1.setImagen("anonimo1.png");
			
			
			Vacante vacante2= new Vacante();
			vacante2.setId(2);
			vacante2.setNombre("Ingeniero");
			vacante2.setDescripcion("Junior");
			vacante2.setFecha(sdf.parse("01-04-2020"));
			vacante2.setSalario(10000.0);
			vacante2.setDe(0);
			vacante2.setImagen("anonimo2.png");
			
			Vacante vacante3= new Vacante();
			vacante3.setId(3);
			vacante3.setNombre("Administrativo");
			vacante3.setDescripcion("Ayudante");
			vacante3.setFecha(sdf.parse("04-04-2020"));
			vacante3.setSalario(2000.0);
			vacante3.setDe(0);
			vacante3.setImagen("anonimo3.png");
			
			Vacante vacante4= new Vacante();
			vacante4.setId(4);
			vacante4.setNombre("Operario");
			vacante4.setDescripcion("Albañil");
			vacante4.setFecha(sdf.parse("04-05-2020"));
			vacante4.setSalario(1590.0);
			vacante4.setDe(1);
			vacante4.setImagen("anonimo4.png");
			
			lista.add(vacante1);
			lista.add(vacante2);
			lista.add(vacante3);
			lista.add(vacante4);
			
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		
		
	
		}
	
	@Override
	public List<Vacante> buscarTodas() {
		// TODO Auto-generated method stub
		return null;
	}

}
