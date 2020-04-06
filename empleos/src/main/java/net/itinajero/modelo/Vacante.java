package net.itinajero.modelo;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "vacantes")  //recordar q el nombre de la BBDD lo configuramos en archivo application.properties
public class Vacante {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nombre;
	private String descripcion;
	private Date fecha;
	private Double salario;
	private Integer destacado;
	private String imagen = "no-image,png"; // por defecto va a tener este valor
	private String estatus;
	private String detalles;

	//@Transient // Con esto ignoramos el binding a una clase foranea, en este caso categoria. Si
				// no lo ponemos, al mandar llamar el metodo findAll tendermos un error al
				// correr el programa. Esto lo explico primero, por lo q se usa sin el resto de anotaciones OnetoOne JoinColumn, etc
	
	@OneToOne //una vacante tiene una categoria
	@JoinColumn(name="idCategoria") //se une con una llave foranea de la tabla SQL, en este caso idCategoria
	private Categoria categoria;

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public String getEstatus() {
		return estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	public String getDetalles() {
		return detalles;
	}

	public void setDetalles(String detalles) {
		this.detalles = detalles;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public Integer getDestacado() {
		return destacado;
	}

	public void setDestacado(Integer destacado) {
		this.destacado = destacado;
	}

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

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Double getSalario() {
		return salario;
	}

	public void setSalario(Double salario) {
		this.salario = salario;
	}

	@Override
	public String toString() {
		return "Vacante [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", fecha=" + fecha
				+ ", salario=" + salario + ", destacado=" + destacado + ", imagen=" + imagen + ", estatus=" + estatus
				+ ", detalles=" + detalles + ", categoria=" + categoria + "]";
	}

}
