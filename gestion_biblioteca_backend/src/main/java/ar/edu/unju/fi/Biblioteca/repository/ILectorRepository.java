package ar.edu.unju.fi.Biblioteca.repository;


import java.util.List;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unju.fi.Biblioteca.model.Lector;

@Repository
public interface ILectorRepository extends CrudRepository <Lector,Long>{
	
	List<Lector> findByNombreContainingIgnoreCase (String nombre);

}
