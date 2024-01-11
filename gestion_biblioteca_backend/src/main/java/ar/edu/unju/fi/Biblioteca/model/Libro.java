package ar.edu.unju.fi.Biblioteca.model;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import ar.edu.unju.fi.Biblioteca.enums.EstadoLibro;
import ar.edu.unju.fi.Biblioteca.enums.TipoLibro;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;


import jakarta.persistence.OneToOne;

@Entity
public class Libro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;	
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_isbn", nullable = false,unique = true) //campo no null y unico 
	private Isbn isbn;
	
	@Column(name="titulo")
    private String titulo;
	
	@Column(name="editorial")
    private String aditorial;
	
	@Column(name="tipo_libro")
	@Enumerated(EnumType.STRING) 
    private TipoLibro tipo; // NOVELA,TEATRO,POESIA,ENSAYO;
	
    @Column(name="anio")
    private Integer anio;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_autor",nullable = false)
    private Autor autor ; 

    @Enumerated(EnumType.STRING) 
    private EstadoLibro estado; //PRESTADO,DISPONIBLE

    
	public Libro() {
		super();
	}

	public Libro(Isbn isbn, String titulo, String aditorial, TipoLibro tipo, Integer anio, Autor autor,
			EstadoLibro estado) {
		super();
		this.isbn = isbn;
		this.titulo = titulo;
		this.aditorial = aditorial;
		this.tipo = tipo;
		this.anio = anio;
		this.autor = autor;
		this.estado = estado;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public EstadoLibro getEstado() {
		return estado;
	}

	public Isbn getIsbn() {
		return isbn;
	}
	public void setIsbn(Isbn isbn) {
		this.isbn = isbn;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getAditorial() {
		return aditorial;
	}
	public void setAditorial(String aditorial) {
		this.aditorial = aditorial;
	}
	public TipoLibro getTipo() {
		return tipo;
	}
	public void setTipo(TipoLibro tipo) {
		this.tipo = tipo;
	}
	public Integer getAnio() {
		return anio;
	}
	public void setAnio(Integer anio) {
		this.anio = anio;
	}
	public Autor getAutor() {
		return autor;
	}
	public void setAutor(Autor autor) {
		this.autor = autor;
	}
	public EstadoLibro isEstado() {
		return estado;
	}
	public void setEstado(EstadoLibro estado) {
		this.estado = estado;
	}
	
	@Override
	public String toString() {
		return "Libro [isbn=" + isbn + ", titulo=" + titulo + ", aditorial=" + aditorial + ", tipo=" + tipo + ", anio="
				+ anio + ", autor=" + autor + ", estado=" + estado + "]";
	}
}
