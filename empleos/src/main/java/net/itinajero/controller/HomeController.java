package net.itinajero.controller;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.itinajero.modelo.Perfil;
import net.itinajero.modelo.Usuario;
import net.itinajero.modelo.Vacante;
import net.itinajero.service.ICategoriasService;
import net.itinajero.service.IUsuariosService;
import net.itinajero.service.IVacantesService;

@Controller
public class HomeController {
	
	//INYECCION DE DEPENDENCIAS
	
	@Autowired
	private ICategoriasService serviceCategorias;
	
	@Autowired
	private IVacantesService serviceVacantes;
	

	@Autowired
	private IUsuariosService serviceUsuarios;
	
	@Autowired
	private PasswordEncoder passwordEncoder; //notar que PasswordEncoder no fue definido en clase de servicio, sino en DatabaseWebSecurity mediante @Bean
	
	
	
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
	
	//Renderiza nuestro formulario personalizado
	@GetMapping("/login" )
	public String mostrarLogin() {
	return "formLogin";
	}
	
	//Para encriptar cualquier texto. No se ocupa en la aplicacion misma.
	@GetMapping("/bcrypt/{texto}")
	@ResponseBody //permite devolver un texto y no una vista
	public String encriptar(@PathVariable("texto") String texto) {
		return texto + "Encriptado en Bcrypt: " + passwordEncoder.encode(texto);
		
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
	
	//Al presionar el boton INGRESAR en el menu
	@GetMapping("/index")
	public String mostrarIndex(Authentication auth, HttpSession session) {
		String username = auth.getName();//recuperar nombre del usuario
		System.out.println("Nombre del usuario: " + username);
		
		
		for (GrantedAuthority rol : auth.getAuthorities()) { //recuperar roles del usuario
			System.out.println("Rol: " + rol);
			
		}
		
		if (session.getAttribute("usuario")==null) { //si no existe la sesion, la creamos
			
		
		
		Usuario usuario = serviceUsuarios.buscarPorUsername(username);
		usuario.setPassword(null);//definimos que no estara almacenada la contraseña en la sesion
		System.out.println("usuario: " + usuario);
		
		session.setAttribute("usuario", usuario);//almacenamos datos en la sesion del usuario
		}
		return "redirect:/";
	}
	
	//Al hacer click en boton Registrarse en el menu
	@GetMapping("/signup")
	public String registrarse(Usuario usuario) {
		return "usuarios/formRegistro";
	}
	
	//Al hacer click en boton Registrarse en el formulario de registro
	@PostMapping("/signup")
	public String guardarRegistro(Usuario usuario, RedirectAttributes attributes) {
		
		String pwdPlano = usuario.getPassword();
		String pwdEncriptado= passwordEncoder.encode(pwdPlano);//aca encriptamos la contraseña
		usuario.setPassword(pwdEncriptado);
		usuario.setEstatus(1); // Activado por defecto
		usuario.setFechaRegistro(new Date()); // Fecha de Registro, la fecha actual del servidor
		
		// Creamos el Perfil que le asignaremos al usuario nuevo
		Perfil perfil = new Perfil();
		perfil.setId(3); // Perfil USUARIO
		usuario.agregar(perfil);
		
		/**
		 * Guardamos el usuario en la base de datos. El Perfil se guarda automaticamente
		 */
		serviceUsuarios.guardar(usuario);				
		attributes.addFlashAttribute("msg", "El registro fue guardado correctamente!");
		
		return "redirect:/usuarios/index";
	}
	
	//Metodo que se ejecuta al enviar el formulario de busqueda de vacantes
	@GetMapping("/search")
	public String buscar(@ModelAttribute ("search") Vacante vacante, Model model) { //Data Binding, con @ModelAttribute ("search") lo que hay en el modelo y con Vacante vacante lo q llega del formulario
		System.out.println("Buscando por: " + vacante);
		
		//Para q el usuario haga una busqueda por alguna palabra en especifico en el input de la descripcion
		ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("descripcion", ExampleMatcher.GenericPropertyMatchers.contains());
		
		//Example<Vacante> example =Example.of(vacante); //metodo of crea un example, que es un objeto formado con los atributos que no son nulos, es decir los filtros (atributos) que SI hemos seleccionado en la busqueda por filtros
		Example<Vacante> example =Example.of(vacante, matcher); 
		List<Vacante> lista = serviceVacantes.buscarByExample(example); 
		model.addAttribute("vacantes", lista);
		return "home";
		
	}
	
	//METODO PERSONALIZADO QUE SI DETECTA STRING VACIOS AL ENVIAR EL FORMULARIO LOS SETEA A NULL. ESTO ES UTIL PARA BUSCAR SOLO POR UN FILTRO (CATEGORIA POR EJEMPLO), DEJANDO LOS OTROS VACIOS (DESCRIPCION VACIA POR EJEMPLO)
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}
	
	
	
	@ModelAttribute //con esto declaramos atributos al modelo que estaran disponibles y presentes implicitamente en cada uno de los metodos de la clase HomeController
	public void setGenericos(Model model) {
		Vacante vacanteSearch = new Vacante(); //para buscar vacantes de trabajo
		vacanteSearch.reset(); //metodo definido en la clase de modelo Vacante para omitir imagenes en la busqueda
		model.addAttribute("vacantes", serviceVacantes.buscarDestacadas());
		model.addAttribute("categorias", serviceCategorias.buscarTodas());
		model.addAttribute("usuarios", serviceUsuarios.buscarTodos());
		model.addAttribute("search", vacanteSearch);
	}
	
	
		
	}

	
	

