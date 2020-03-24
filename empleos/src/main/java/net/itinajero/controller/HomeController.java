package net.itinajero.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import net.itinajero.modelo.Vacante;

@Controller
public class HomeController {
	
	@GetMapping("/tabla")
	public String mostrarTabla (Model model) {
		List <Vacante>lista=this.getVacantes();
		model.addAttribute("vacantes", lista);
		return "tabla";
	}
	
	
	
	@GetMapping("/detalle")
	public String detalle (Model model) {
		Vacante vacante = new Vacante () ;
		vacante.setId(0);
		vacante.setNombre("Ingeniero de sistemas");
		vacante.setDescripcion("Ingeniero senior");
		vacante.setFecha(null);
		vacante.setSalario(9700.0);
		model.addAttribute("vacante", vacante);
		
		
		return "detalle";
		
		
	}
	
	@GetMapping("/listado")
	public String mostrarListado(Model model) {
		
		List<String>lista = new LinkedList<String>();
		lista.add("Ingeniero de sistemas");
		lista.add("Auxiliar contabilidad");
		lista.add("Vendedor");
		lista.add("Arquitecto");
		model.addAttribute("empleos", lista);
		return "listado"; //en spring en el controlado se puede retornar un objeto (html) que no coincide con el tipo definido inicialmente en el metodo
		
	}
	
	
	@GetMapping("/")
	public String mostrarHome(Model model) {
		/*
		 * model.addAttribute("mensaje", "Bienvenido a nuestra app");//al igual q en
		 * springMVS "mensaje" va a la vista thymeleaf y "Bienvenido..." es un objeto
		 * (en este caso un string) model.addAttribute("fecha", new Date()); //en este
		 * caso el objeto es una fecha
		 */		
		
		String titulo = "Auxiliar de contabilidad";
		Date fecha = new Date();
		Double salario = 9999.0;
		Boolean vigente = true;
		
		model.addAttribute("titulo", titulo );
		model.addAttribute("fecha", fecha );
		model.addAttribute("salario", salario );
		model.addAttribute("vigente", vigente);
		return "home"; //nunca se necesita la extension. en spring en el controlado se puede retornar un objeto (html) que no coincide con el tipo definido inicialmente en el metodo
		
	}
	
	private List<Vacante> getVacantes(){
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		List<Vacante>lista=new LinkedList<Vacante>();
		
		
		try {
			
			Vacante vacante1= new Vacante();
			vacante1.setId(1);
			vacante1.setNombre("Medico");
			vacante1.setDescripcion("Internista");
			vacante1.setFecha(sdf.parse("02-03-2020"));
			vacante1.setSalario(12000.0);
			
			
			Vacante vacante2= new Vacante();
			vacante2.setId(2);
			vacante2.setNombre("Ingeniero");
			vacante2.setDescripcion("Junior");
			vacante2.setFecha(sdf.parse("01-04-2020"));
			vacante2.setSalario(10000.0);
			
			
			Vacante vacante3= new Vacante();
			vacante3.setId(3);
			vacante3.setNombre("Administrativo");
			vacante3.setDescripcion("Ayudante");
			vacante3.setFecha(sdf.parse("04-04-2020"));
			vacante3.setSalario(2000.0);
			
			Vacante vacante4= new Vacante();
			vacante4.setId(4);
			vacante4.setNombre("Operario");
			vacante4.setDescripcion("Albañil");
			vacante4.setFecha(sdf.parse("04-05-2020"));
			vacante4.setSalario(1590.0);
			
			lista.add(vacante1);
			lista.add(vacante2);
			lista.add(vacante3);
			lista.add(vacante4);
			
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return lista;
		
		
	
		}
		
	}

	
	

