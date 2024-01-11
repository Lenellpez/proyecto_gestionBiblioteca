package ar.edu.unju.fi.Biblioteca.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Isbn {
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id ; 
	 
	 @Column(name="prefijo")
	 private Integer prefijo;
	 
	 @Column(name="indicadorPais")
	 private Integer indicadorPais;
	 
	 @Column(name="prefijoAutor")
	 private Integer prefijoAutor;
	 
	 @Column(name="indicadorTitulo")
	 private Integer indicadorTitulo;
	 	 
	public Isbn() {
		super();
	}
	
	public Isbn(Integer prefijo, Integer indicadorPais, Integer prefijoAutor, Integer indicadorTitulo) {
		super();
		this.prefijo = prefijo;
		this.indicadorPais = indicadorPais;
		this.prefijoAutor = prefijoAutor;
		this.indicadorTitulo = indicadorTitulo;
	}

	public Integer getPrefijo() {
		return prefijo;
	}
	public void setPrefijo(Integer prefijo) {
		this.prefijo = prefijo;
	}
	public Integer getIndicadorPais() {
		return indicadorPais;
	}
	public void setIndicadorPais(Integer indicadorPais) {
		this.indicadorPais = indicadorPais;
	}
	public Integer getPrefijoAutor() {
		return prefijoAutor;
	}
	public void setPrefijoAutor(Integer prefijoAutor) {
		this.prefijoAutor = prefijoAutor;
	}
	public Integer getIndicadorTitulo() {
		return indicadorTitulo;
	}
	public void setIndicadorTitulo(Integer indicadorTitulo) {
		this.indicadorTitulo = indicadorTitulo;
	}
	
	@Override
	public String toString() {
		return "Isbn [id=" + id + ", prefijo=" + prefijo + ", indicadorPais=" + indicadorPais + ", prefijoAutor="
				+ prefijoAutor + ", indicadorTitulo=" + indicadorTitulo + "]";
	}

}
