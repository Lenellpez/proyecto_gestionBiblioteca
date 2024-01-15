package ar.edu.unju.fi.Biblioteca.dto;

import java.io.Serializable;

public class LectorDto implements Serializable {
	private static final long serialVersionUID = 1L;
    
    private String tipo; // "asociado" o "noAsociado"
    private String nombre;
    private String telefono;
    private Integer numero; // cuil o nroSocio 
    
	public LectorDto(String tipo, String nombre, String telefono, Integer numero) {
		super();
		this.tipo = tipo;
		this.nombre = nombre;
		this.telefono = telefono;
		this.numero = numero;
	}
	
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
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
	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}

}
