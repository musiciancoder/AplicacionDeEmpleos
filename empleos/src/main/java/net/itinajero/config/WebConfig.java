package net.itinajero.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//CONFIGURACION PARA ENVIAR ARCHIVOS SUBIDOS A UNA CARPETA DIFERENTE A LA CARPETA POR DEFECTO RESOURCES/STATIC/IMAGE
@Configuration
public class WebConfig implements WebMvcConfigurer{
	
	@Value("${empleosapp.ruta.imagenes}")//definido en archivo application.properties
	private String rutaImagenes;
public void addResourceHandlers(ResourceHandlerRegistry registry) {
//registry.addResourceHandler("/logos/**").addResourceLocations("file:/empleos/img-vacantes/"); // Linux
registry.addResourceHandler("/logos/**").addResourceLocations("file:"+rutaImagenes); // Windows. Le damos un alias a la carpeta (logos). Este alias es ocupado en las vistas home.html y detalles.html
}
}
