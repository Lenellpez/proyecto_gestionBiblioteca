package ar.edu.unju.fi.Biblioteca.repository;


import ar.edu.unju.fi.Biblioteca.enums.EstadoPrestamo;
import ar.edu.unju.fi.Biblioteca.model.Lector;
import ar.edu.unju.fi.Biblioteca.model.Prestamo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPrestamoRepository  extends CrudRepository <Prestamo , Long > {

	//buscar prestamos asociado a  un  lector
	List<Prestamo> findByLector (Lector lector);
	//buscar los prestamos activos del lector 
	 List<Prestamo> findByLectorAndEstado(Lector lector, EstadoPrestamo estado);
	 
	 public long countByLectorAndEstado(Lector lector, EstadoPrestamo estado);
 
} 