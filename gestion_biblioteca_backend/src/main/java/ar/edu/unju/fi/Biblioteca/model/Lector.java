package ar.edu.unju.fi.Biblioteca.model;

import ar.edu.unju.fi.Biblioteca.enums.EstadoLector;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)//los campos específicos de cada subclase estarán en esa tabla
@DiscriminatorColumn(name = "clase")//discriminador es una columna que indica la clase concreta a la que pertenece cada registro en la tabla de herencia
public abstract class Lector {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name="nombre")
    private String nombre;
	
	@Column(name="telefono")
    private String telefono;
	
	@Enumerated(EnumType.STRING) 
	@Column(name="estado_lec")
	private EstadoLector estado;   //MULTADO ,NO_MULTADO;

	public Lector() {
		super();
	}

	public Lector(String nombre, String telefono, EstadoLector estado) {
		super();
		this.nombre = nombre;
		this.telefono = telefono;
		this.estado = estado;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public EstadoLector getEstado() {
		return estado;
	}

	public void setEstado(EstadoLector estado) {
		this.estado = estado;
	}

	// Método abstracto para calcular la cantidad máxima de días de préstamo
	 public abstract  int getMaxDiasPrestamo();
    
 // Método abstracto para calcular la cantidad máxima de libros en préstamo
    public abstract int getMaxLibrosPrestamo();

	@Override
	public String toString() {
		return "Lector [id=" + id + ", nombre=" + nombre + ", telefono=" + telefono + ", estado=" + estado + "]";
	}

}
