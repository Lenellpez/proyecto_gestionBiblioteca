package ar.edu.unju.fi.Biblioteca.model;

import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import ar.edu.unju.fi.Biblioteca.enums.EstadoPrestamo;
import ar.edu.unju.fi.Biblioteca.util.FechaUtil;

@Entity
public class Prestamo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="fecha_prestamo")
	private LocalDate fechaPrestamo ; 
	
	@Column(name="fecha_devolucion")
	private LocalDate   fechaDevolucion;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_libro")
	private Libro libro;
	
	@Enumerated(EnumType.STRING) 
	private EstadoPrestamo estado;  //PRESTADO o DEVUELTO
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_lector")
	private Lector lector;

	   public Prestamo() {
		super();
	}

	public Prestamo(Lector lector, Libro libro, LocalDate fechaPrestamo, LocalDate fechaDevolucion, EstadoPrestamo estado) {
	        this.lector = lector;
	        this.libro = libro;
	        this.fechaPrestamo = fechaPrestamo;
	        this.fechaDevolucion = fechaDevolucion;
	        this.estado = estado;
     	}

	public Prestamo(Long id, Lector lector, Libro libro, LocalDate fechaPrestamo, LocalDate fechaDevolucion, EstadoPrestamo estado) {
	        this.id = id;
	        this.lector = lector;
	        this.libro = libro;
	        this.fechaPrestamo = fechaPrestamo;
	        this.fechaDevolucion = fechaDevolucion;
	        this.estado = estado;
	    }


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public LocalDate getFechaPrestamo() {
		return fechaPrestamo;
	}


	public void setFechaPrestamo(LocalDate fechaPrestamo) {
		this.fechaPrestamo = fechaPrestamo;
	}


	public LocalDate getFechaDevolucion() {
		return fechaDevolucion;
	}


	public void setFechaDevolucion(LocalDate fechaDevolucion) {
		this.fechaDevolucion = fechaDevolucion;
	}


	public EstadoPrestamo getEstado() {
		return estado;
	}


	public void setEstado(EstadoPrestamo estado) {
		this.estado = estado;
	}


	public Libro getLibro() {
		return libro;
	}


	public void setLibro(Libro libro) {
		this.libro = libro;
	}


	public Lector getLector() {
		return lector;
	}


	public void setLector(Lector lector) {
		this.lector = lector;
	}

	@Override
	public String toString() {
		return "Prestamo [id=" + id + ", fechaPrestamo=" + FechaUtil.formatearFecha(fechaPrestamo)   + ", fechaDevolucion=" + FechaUtil.formatearFecha(fechaDevolucion) 
				+ ", estado=" + estado + ", libro=" + libro + ", lector=" + lector + "]";
	}

}


