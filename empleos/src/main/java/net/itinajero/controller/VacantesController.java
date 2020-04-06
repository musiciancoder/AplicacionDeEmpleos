package net.itinajero.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.itinajero.modelo.Categoria;
import net.itinajero.modelo.Vacante;
import net.itinajero.service.ICategoriasService;
import net.itinajero.service.IVacantesService;
import net.itinajero.util.Utileria;

@Controller
@RequestMapping("/vacantes")
public class VacantesController {

	// Con @Value definimos una ruta para guardar archivos en nuestra app cuando el
	// usuario los suba. Previamente debemos configurar en archivo
	// resources/aplication.properties
	@Value("{$empleosapp.ruta.imagenes}")
	private String ruta;

	// INYECCION DE DEPENDENCIAS
	@Autowired
	private IVacantesService serviceVacantes;

	@Autowired
	private ICategoriasService serviceCategorias;

	// Solucion método mostrarIndex

	@GetMapping("/index")
	public String mostrarIndex(Model model) {
		List<Vacante> lista = serviceVacantes.buscarTodas();
		model.addAttribute("vacantes", lista);
		return "vacantes/listVacantes";
	}

	@GetMapping("/create")
	public String crear(Vacante vacante, Model model) { // Se pasa como argumento un objeto de la clase modelo, esto
														// (conjuntamente con la anotacion th:object="${vacante}" en el
														// formulario) tiene como funcion vincular el formulario con la
														// clase modelo Vacante. Es necesario, sino la app se cae
		List<Categoria> lista = serviceCategorias.buscarTodas();
		model.addAttribute("categorias", lista);
		return "vacantes/formVacante";
	}

	/*
	 * METODO Q SE EJECUTA AL HACER CLICK EN BOTON GUARDAR, EN FORMULARIO PARA CREAR
	 * VACANTE; ASI SE HACE CON SPRING BOOT !!
	 */
	@PostMapping("/save")
	public String guardar(Vacante vacante, BindingResult result, RedirectAttributes attributes,
			@RequestParam("archivoImagen") MultipartFile multiPart) { // notese que se usa @RequestParam solo para la
																		// subida de archivos, pero no para binding en
																		// inputs tipo text, solo basta con un objeto de
																		// la clase
		// modelo (Vacante en este caso). BINDING RESULT tiene los metodos para control
		// de errores en formularios (por ejemplo si se escribe un String en ve

		if (result.hasErrors()) {
			for (ObjectError error : result.getAllErrors()) {
				System.out.println("Ocurrio un error: " + error.getDefaultMessage());
			}
			return "vacantes/formVacante";
		}

		// CODIGO PARA SUBIR IMAGENES EN EL INPUT CON NAME archivoImagen

		if (!multiPart.isEmpty()) {
			// String ruta = "/empleos/img-vacantes/"; // Linux/MAC
			// String ruta = "c:/empleos/img-vacantes/"; // Windows
			String nombreImagen = Utileria.guardarArchivo(multiPart, ruta);
			if (nombreImagen != null) { // La imagen si se subio
				// Procesamos la variable nombreImagen
				vacante.setImagen(nombreImagen);
			}
		}

		//

		serviceVacantes.guardar(vacante);
		attributes.addFlashAttribute("msg", "Registro Guardado"); // FlashAtributes se ocupa en vez de
																	// model.Addatributecuando estamos redireccionando a
																	// otro metodo en return. Se mostrara el mensaje en
																	// la vista solo si guardamos una nueva vacante
																	// exitosamente

		System.out.println("Vacante: " + vacante);

		return "redirect:/vacantes/index"; // si escribimos return "vacantes/list" no va a renderizar el nuevo objeto q
											// hemos guardado al enviar el formulario, porque en este metodo no hay
											// ningun model.addAtribute y por lo mismo debemos redireccionar al otro
											// metodo que si lo tiene
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

	//Al hacer click en boton detalle
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
