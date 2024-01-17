package ar.edu.unju.fi.Biblioteca.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ar.edu.unju.fi.Biblioteca.model.Isbn;
import ar.edu.unju.fi.Biblioteca.model.Libro;

public interface ILibroRepository   extends CrudRepository <Libro,Long>{
	
     public Libro findByIsbn(Isbn isbn);
	
	 @Query(value = "SELECT * FROM Libro l INNER JOIN Autor a ON l.id = a.libro_id WHERE a.nombre = :autorNombre", nativeQuery = true)
	 public List<Libro> findByAutoresNombre(@Param("autorNombre") String autorNombre);
	 
	 public List<Libro> findByTituloIgnoreCase(String titulo);
	 
	 @Query("SELECT COUNT(l) FROM Libro l WHERE l.estado = 'DISPONIBLE'")
	 Long contarLibrosDisponibles();
	 
	 @Query("SELECT COUNT(l) FROM Libro l WHERE l.estado = 'NO_DISPONIBLE'")
	 Long contarLibrosNoDisponibles();
}
