package net.itinajero.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	
	@GetMapping("/")
	public String mostrarHome() {
		return "home"; //nunca se necesita la extension
	}

	
	
}
