package net.itinajero.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="categorias") //nombre de la tabla en MYSQL. //recordar q el nombre de la BBDD lo configuramos en archivo application.properties
public class Categoria {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)//para que el id sea autoincrementable. IDENTITY ES SOLO PARA MYSQL.
	private Integer id;//este es el idCategoria de la tabla vacantes. Notese que aca simplemente lo definimos como id.
	private String nombre;
	private String descripcion;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	@Override
	public String toString() {
		return "Categoria [idCategoria=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + "]";
	}

}
