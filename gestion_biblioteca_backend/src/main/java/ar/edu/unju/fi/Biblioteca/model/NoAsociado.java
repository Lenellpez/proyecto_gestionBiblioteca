package ar.edu.unju.fi.Biblioteca.model;

import ar.edu.unju.fi.Biblioteca.enums.EstadoLector;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue(value = "NO_ASC")
public class NoAsociado extends Lector {

	@Column(name = "nro_cuil") 
	public Integer nroCuil;

	public NoAsociado() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NoAsociado( String nombre, String telefono, EstadoLector estado,Integer nroCuil) {
		super(nombre, telefono, estado);
		this.nroCuil = nroCuil;
	}


    @Override
    public int getMaxDiasPrestamo() {
        return 2;
    }

    @Override
    public int getMaxLibrosPrestamo() {
        return 3;
    }
    
   
}
