package net.itinajero.modelo;


public class Categoria {
	
	private Integer Id;
	private String nombre;
	private String descripcion;
	public Integer getId() {
		return Id;
	}
	public void setId(Integer Id) {
		this.Id = Id;
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
		return "Categoria [idCategoria=" + Id + ", nombre=" + nombre + ", descripcion=" + descripcion + "]";
	}

}
