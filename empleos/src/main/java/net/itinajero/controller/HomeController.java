package net.itinajero.controller;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.itinajero.modelo.Categoria;
import net.itinajero.modelo.Usuario;
import net.itinajero.modelo.Vacante;
import net.itinajero.service.ICategoriasService;
import net.itinajero.service.IVacantesService;

@Controller
public class HomeController {
	
	//INYECCION DE DEPENDENCIAS
	@Autowired
	private IVacantesService serviceVacantes;
	
	@Autowired
	private ICategoriasService serviceCategorias;
	
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
		
		/*ESTE CODIGO LO ELIMINO AL DECLARAR MAS ABAJO EL METODO setGenericos(Model model)
		//EJECUCION DE INY DE DEPENDENCIAS CON serviceVacantes
		List <Vacante>lista= serviceVacantes.buscarTodas() ; 
		model.addAttribute("vacantes", lista);
		*/
		return "home"; //nunca se necesita la extension. en spring en el controlado se puede retornar un objeto (html) que no coincide con el tipo definido inicialmente en el metodo
		
	}
	
	
	
	@GetMapping("/signup")
	public String registrarse(Usuario usuario) {
		return "usuarios/formRegistro";
	}
	
	@PostMapping("/signup")
	public String guardarRegistro(Usuario usuario, RedirectAttributes attributes) {
		return "redirect:/usuarios/index";
	}
	
	
	@ModelAttribute //con esto declaramos atributos al modelo que estaran disponibles para todos los metodos de la clase HomeController
	public void setGenericos(Model model) {
		model.addAttribute("vacantes", serviceVacantes.buscarDestacadas());
		model.addAttribute("categorias", serviceCategorias.buscarTodas());
	}
	
	
		
	}

	
	

