package ar.edu.unju.fi.Biblioteca.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unju.fi.Biblioteca.model.Isbn;

@Repository
public interface IIsbnRepository extends CrudRepository <Isbn,Long> {

	public Isbn findByPrefijoAndIndicadorPaisAndPrefijoAutorAndIndicadorTitulo( 
			int prefijo, int idPais, int prefijoAutor, int idTitulo );
}