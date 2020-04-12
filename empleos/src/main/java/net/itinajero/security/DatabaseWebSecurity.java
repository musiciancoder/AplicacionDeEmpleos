package net.itinajero.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//CLASE Q PERMITE A LOS USUARIOS Q TENEMOS EN NUESTRA BBDD MYSQL HACER LOGIN

@Configuration
@EnableWebSecurity
public class DatabaseWebSecurity extends WebSecurityConfigurerAdapter {
	@Autowired
	private DataSource dataSource; // DataSource es la bbdd mysql empleosdb que tenemos configurada en archivo
									// aplications.properties

	// Los usuarios los saca de la tabla usuarios y los authorities (roles) de la
	// tabla intermedia usuarioperfil
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource)
				.usersByUsernameQuery("select username, password, estatus from Usuarios where username=?")
				.authoritiesByUsernameQuery("select u.username, p.perfil from UsuarioPerfil up "
						+ "inner join Usuarios u on u.id = up.idUsuario "
						+ "inner join Perfiles p on p.id = up.idPerfil " + "where u.username = ?");
	}

	// Personalizar accesos a URLs
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				// Los recursos estáticos no requieren autenticación
				.antMatchers("/bootstrap/**", "/images/**", "/tinymce/**", "/logos/**").permitAll()
				// Las vistas públicas no requieren autenticación
				.antMatchers("/", "/signup", "/search", "/bcrypt/**", "/vacantes/view/**").permitAll()
				
				// Asignar permisos a URLs por ROLES
				.antMatchers("/vacantes/**").hasAnyAuthority("SUPERVISOR","ADMINISTRADOR")
				.antMatchers("/categorias/**").hasAnyAuthority("SUPERVISOR","ADMINISTRADOR")
				.antMatchers("/usuarios/**").hasAnyAuthority("ADMINISTRADOR")

				
				// Todas las demás URLs de la Aplicación requieren autenticación
				.anyRequest().authenticated()
				// El formulario de Login no requiere autenticacion
				//.and().formLogin().permitAll(); //Esto es para el formulario por defecto de Spring
				.and().formLogin().loginPage("/login").permitAll();//Esto es para el formulario que nosotros hemos personalizado
	}
	
	//Encriptar contraseñas
	@Bean //Esto permite crear un bean, por lo que llamamos a este metodo directamente en HomeController por medio de @Autowired PasswordEncoder passwordEncoder;
	public PasswordEncoder passwordEncoder() {
	return new BCryptPasswordEncoder();
	}

	

}