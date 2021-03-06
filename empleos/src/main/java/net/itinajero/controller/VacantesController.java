package net.itinajero.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.itinajero.modelo.Vacante;
import net.itinajero.service.IVacantesService;

@Controller
@RequestMapping("/vacantes")
public class VacantesController {

	// INYECCION DE DEPENDENCIAS
	@Autowired
	private IVacantesService serviceVacantes;
	
	@GetMapping("/index")
	public String mostrarIndex(Model model) {
	List <Vacante> lista= serviceVacantes.buscarTodas();
	model.addAttribute("listaDeVacantes", lista);
		
		return "vacantes/listVacantes";
		
	}

	@GetMapping("/create")
	public String crear() {

		return "vacantes/formVacante";
	}

	/*
	 * METODO Q SE EJECUTA AL HACER CLICK EN BOTON GUARDAR, EN FORMULARIO PARA CREAR
	 * VACANTE; ASI SE HACE CON SPRING BOOT !!
	 */
	@PostMapping("/save")
	public String guardar(Vacante vacante,  BindingResult result) { // notese que no se usa @RequestParam, solo basta con un objeto de la clase
												// modelo (Vacante en este caso). BINDING RESULT tiene los metodos para control de errores en formularios (por ejemplo si se escribe un String en vez de un double)
		if (result.hasErrors()) {
			return "vacantes/formVacante";
			}

		serviceVacantes.guardar(vacante);
		System.out.println("Vacante: " + vacante);

		return "vacantes/listVacantes";
	}
	/*
	 * METODO Q SE EJECUTA AL HACER CLICK EN BOTON GUARDAR, EN FORMULARIO PARA CREAR
	 * VACANTE; ASI SE HACE CON SPRING MVC, PERO NO CON SPRING BOOT
	 * 
	 * @PostMapping("/save") public String guardar(@RequestParam("nombre") String
	 * nombre, @RequestParam("descripcion") String descripcion ){
	 * System.out.println("Nombre: " + nombre); System.out.println("Descripcion :" +
	 * descripcion);
	 * 
	 * 
	 * return "vacantes/listVacantes"; }
	 */

	@GetMapping("/delete") // para llamar con vacantes/delete?id=3, por ejemplo. Notese que el nombre del
							// atributo Si viaja junto al valor de este. Al pasar el mouse por encima del
							// link SI se ve la url a la que nos dirigira si lo presionamos
	public String eliminar(@RequestParam("id") int idVacante, Model model) { // @RequestParam es mas apropiado para
																				// manipular bases de datos a traves de
																				// botones en nuestras vistas html
		System.out.println("Eliminando vacante con id :" + idVacante);
		model.addAttribute("id", idVacante);
		return "mensaje";
	}

	@GetMapping("/view/{id}") // para llamar con vacantes/view/3, por ejemplo. Notese que el nombre del
								// atributo NO viaja, solo el valor de este. Al pasar el mouse por encima del
								// link NO se ve nada
	public String verDetalle(@PathVariable("id") int idVacante, Model model) { // @PathVariable es mas apropiado para
																				// expandir y renderizar detalles de un
																				// objeto

		Vacante vacante = serviceVacantes.buscarPorId(idVacante);

		System.out.println("Vacante: " + vacante);
		model.addAttribute("vacante", vacante);
		// TODO Buscar los datos de la vacante en la BBDD

		return "detalle";

	}

	// @InitBinder permite customizar el formato de entrada de algun dato (en este
	// caso la fecha del formulario)
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}

}
