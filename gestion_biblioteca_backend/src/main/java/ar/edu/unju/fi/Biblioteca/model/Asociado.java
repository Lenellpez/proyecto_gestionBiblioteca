package ar.edu.unju.fi.Biblioteca.model;

import ar.edu.unju.fi.Biblioteca.enums.EstadoLector;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue(value = "ASC")
public class Asociado extends  Lector{
	
	@Column(name = "nro_socio")
	private Integer nroSocio;

	public Asociado() {
		super();
	}

	public Asociado(String nombre, String telefono, EstadoLector estado,Integer nroSocio) {
		super( nombre, telefono, estado);
		this.nroSocio = nroSocio;
	}

	public Integer getNroSocio() {
		return nroSocio;
	}

	public void setNroSocio(Integer nroSocio) {
		this.nroSocio = nroSocio;
	}

	  @Override
	    public int getMaxDiasPrestamo() {
	        return 7;
	    }
	  
	   @Override
	    public int getMaxLibrosPrestamo() {
	        return 5;
	    }

	@Override
	public String toString() {
		return "Asociado [nroSocio=" + nroSocio + "]" + super.toString();
	}
   
}
