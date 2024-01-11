package ar.edu.unju.fi.Biblioteca.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unju.fi.Biblioteca.model.Autor;

@Repository
public interface IAutorRepository extends CrudRepository <Autor,Long> {

}
