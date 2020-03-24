package net.itinajero.controller;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	
	@GetMapping("/")
	public String mostrarHome(Model model) {
		model.addAttribute("mensaje", "Bienvenido a nuestra app");//al igual q en springMVS "mensaje" va a la vista thymeleaf y "Bienvenido..." es un objeto (en este caso un string)
		model.addAttribute("fecha", new Date()); //en este caso el objeto es una fecha
		return "home"; //nunca se necesita la extension
	}

	
	
}
