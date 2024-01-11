package ar.edu.unju.fi.Biblioteca.dto;
import java.io.Serializable;
public class IsbnDto  implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer prefijo;
	private Integer prefPais;
	private Integer prefAutor;
	private Integer prefTitulo;
	
	public IsbnDto(Integer prefijo, Integer prefPais, Integer prefAutor, Integer prefTitulo) {
		super();
		this.prefijo = prefijo;
		this.prefPais = prefPais;
		this.prefAutor = prefAutor;
		this.prefTitulo = prefTitulo;
	}
	
	public Integer getPrefijo() {
		return prefijo;
	}
	public void setPrefijo(Integer prefijo) {
		this.prefijo = prefijo;
	}
	public Integer getPrefPais() {
		return prefPais;
	}
	public void setPrefPais(Integer prefPais) {
		this.prefPais = prefPais;
	}
	public Integer getPrefAutor() {
		return prefAutor;
	}
	public void setPrefAutor(Integer prefAutor) {
		this.prefAutor = prefAutor;
	}
	public Integer getPrefTitulo() {
		return prefTitulo;
	}
	public void setPrefTitulo(Integer prefTitulo) {
		this.prefTitulo = prefTitulo;
	}
}
