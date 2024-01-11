package ar.edu.unju.fi.Biblioteca.dto;

import java.io.Serializable;

import ar.edu.unju.fi.Biblioteca.enums.TipoLibro;

public class LibroDto implements Serializable {
    
	private static final long serialVersionUID = 1L;
	
	private int anio ;
	private String titulo;
	private TipoLibro tipo;
	private Long idAutor;
	private String editorial;
	//valores para el isbn
	private Integer isbnPrefijo;
	private Integer isbnPais;
	private Integer isbnPrefijoAutor;
	private Integer isbnTitulo;

	public LibroDto(int anio, String titulo, TipoLibro tipo, Long idAutor, String editorial, Integer isbnPrefijo,
			Integer isbnPais, Integer isbnPrefijoAutor, Integer isbnTitulo) {
		super();
		this.anio = anio;
		this.titulo = titulo;
		this.tipo = tipo;
		this.idAutor = idAutor;
		this.editorial = editorial;
		this.isbnPrefijo = isbnPrefijo;
		this.isbnPais = isbnPais;
		this.isbnPrefijoAutor = isbnPrefijoAutor;
		this.isbnTitulo = isbnTitulo;
	}
	public int getAnio() {
		return anio;
	}
	public void setAnio(int anio) {
		this.anio = anio;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public TipoLibro getTipo() {
		return tipo;
	}
	public void setTipo(TipoLibro tipo) {
		this.tipo = tipo;
	}
	public Long getIdAutor() {
		return idAutor;
	}
	public void setIdAutor(Long idAutor) {
		this.idAutor = idAutor;
	}
	public String getEditorial() {
		return editorial;
	}
	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}
	public Integer getIsbnPrefijo() {
		return isbnPrefijo;
	}
	public void setIsbnPrefijo(Integer isbnPrefijo) {
		this.isbnPrefijo = isbnPrefijo;
	}
	public Integer getIsbnPais() {
		return isbnPais;
	}
	public void setIsbnPais(Integer isbnPais) {
		this.isbnPais = isbnPais;
	}
	public Integer getIsbnPrefijoAutor() {
		return isbnPrefijoAutor;
	}
	public void setIsbnPrefijoAutor(Integer isbnPrefijoAutor) {
		this.isbnPrefijoAutor = isbnPrefijoAutor;
	}
	public Integer getIsbnTitulo() {
		return isbnTitulo;
	}
	public void setIsbnTitulo(Integer isbnTitulo) {
		this.isbnTitulo = isbnTitulo;
	}
	
}
