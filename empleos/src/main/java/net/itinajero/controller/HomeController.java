package net.itinajero.controller;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import net.itinajero.modelo.Vacante;
import net.itinajero.service.IVacantesService;

@Controller
public class HomeController {
	
	//INYECCION DE DEPENDENCIAS
	@Autowired
	private IVacantesService serviceVacantes;
	
	@GetMapping("/tabla")
	public String mostrarTabla (Model model) {
		List <Vacante>lista= serviceVacantes.buscarTodas() ; //USO DE INY DE DEPENDENCIAS
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
	
	
		
	}

	
	

