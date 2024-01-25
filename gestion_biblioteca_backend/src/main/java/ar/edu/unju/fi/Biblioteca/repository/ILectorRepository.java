package ar.edu.unju.fi.Biblioteca.repository;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unju.fi.Biblioteca.enums.EstadoLector;
import ar.edu.unju.fi.Biblioteca.model.Lector;

@Repository
public interface ILectorRepository extends CrudRepository <Lector,Long>{
	
	
	 @Query("SELECT COUNT(l) FROM Lector l WHERE TYPE(l) = Asociado")
	 Long contarAsociados();
	 
	 
	 @Query("SELECT COUNT(l) FROM Lector l WHERE TYPE(l) = NoAsociado")
	 Long contarNoAsociados();
	
	 List<Lector> findByNombreContainingIgnoreCase (String nombre);
	 
	List<Lector> findByEstado (EstadoLector estado);

}
