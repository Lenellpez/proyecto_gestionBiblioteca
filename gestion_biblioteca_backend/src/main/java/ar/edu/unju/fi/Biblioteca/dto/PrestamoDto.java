package ar.edu.unju.fi.Biblioteca.dto;

import java.io.Serializable;

public class PrestamoDto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long idLibro;
	private Long idLector;

	public Long getIdLibro() {
		return idLibro;
	}
	public void setIdLibro(Long idLibro) {
		this.idLibro = idLibro;
	}
	public Long getIdLector() {
		return idLector;
	}
	public void setIdLector(Long idLector) {
		this.idLector = idLector;
	}

	
}
