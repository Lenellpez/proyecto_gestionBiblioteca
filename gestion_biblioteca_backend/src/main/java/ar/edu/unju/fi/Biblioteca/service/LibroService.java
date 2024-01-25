package ar.edu.unju.fi.Biblioteca.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.Biblioteca.dto.IsbnDto;
import ar.edu.unju.fi.Biblioteca.dto.LibroDto;
import ar.edu.unju.fi.Biblioteca.enums.EstadoLibro;
import ar.edu.unju.fi.Biblioteca.exceptions.ManagerException;
import ar.edu.unju.fi.Biblioteca.model.Autor;
import ar.edu.unju.fi.Biblioteca.model.Isbn;
import ar.edu.unju.fi.Biblioteca.model.Libro;
import ar.edu.unju.fi.Biblioteca.repository.IAutorRepository;
import ar.edu.unju.fi.Biblioteca.repository.IIsbnRepository;
import ar.edu.unju.fi.Biblioteca.repository.ILibroRepository;

@Service
public class LibroService {

	@Autowired
	private ILibroRepository libroRepository ;
	
	@Autowired
	private IAutorRepository autorRepository;
	
	@Autowired
	private IIsbnRepository isbnRepository;
	
		
	/**
	 * listar todos libros 
	 * @return lista de libros 
	 */
	public List<Libro> getAll(){
		List<Libro> libros = new ArrayList<>(); 
		libroRepository.findAll().forEach(libros::add);
		return libros;
	}
	
	/**
	 * listar  libros segun su estado (PRESTADO o DISPONIBLE)
	 * @return lista de libros 
	 */
	public List<Libro> getByEstado(EstadoLibro estado){
		return libroRepository.findByEstado(estado);
	}

	/**
	 * realizar carga de un Libo
	 * @param libroDto a cargar 
	 * @throws ManagerException excepción si el isbn del libro ya esta registrado
	 */
    public void guardarLibro(LibroDto libroDto) throws ManagerException {
    	Isbn isbnNuevo= new Isbn(libroDto.getIsbnPrefijo() ,libroDto.getIsbnPais() ,libroDto.getIsbnPrefijoAutor() ,libroDto.getIsbnTitulo() );
    	isbnRepetido(isbnNuevo);//exepction
    	Autor autor = autorRepository.findById(libroDto.getIdAutor()).get();    	
        Libro  nuevoLibro =  new Libro (isbnNuevo, libroDto.getTitulo(),libroDto.getEditorial() ,libroDto.getTipo(),libroDto.getAnio(),autor,EstadoLibro.DISPONIBLE);
        libroRepository.save(nuevoLibro);
   }
    
    
	private void isbnRepetido(Isbn isbn) throws ManagerException {
		Isbn isbnRepetido = isbnRepository.findByPrefijoAndIndicadorPaisAndPrefijoAutorAndIndicadorTitulo(
				isbn.getPrefijo(),isbn.getIndicadorPais(),isbn.getPrefijoAutor(),isbn.getIndicadorPais());
		if (isbnRepetido != null) {
			throw new ManagerException(
					isbnRepetido + " repetido. No se puden alamcenar libros con codigo ISBN repetidos.");
		}
	} 
 
	public void actualizar(Libro libro) {
		libroRepository.save(libro);
	}
    
    /**
     * buscar libros por título
     * @param titulo a buscar 
     * @return lista de libros con el titulo buscado
     */
    public List<Libro> getLibrosByTitulo(String titulo) {
        return libroRepository.findByTituloIgnoreCase(titulo);
    }

    /**
     * Método para buscar libros por autor
     * @param autorNombre nombre del autor del libro buscado
     * @return lista de libros encontrados
     */
    public List<Libro> getLibrosByAutor(String autorNombre) {
        return libroRepository.findByAutoresNombre(autorNombre);
    }
   
    
	/**
	 * Busca un Libro mediante su Isbn. 
	 * @param isbnDto con sus 4 prefijos
	 * @return Libro buscado
	 */
	public Libro buscarIsbn(IsbnDto isbn) {
		Isbn isbnBuscado =isbnRepository.findByPrefijoAndIndicadorPaisAndPrefijoAutorAndIndicadorTitulo(
				isbn.getPrefijo(),isbn.getPrefPais(),isbn.getPrefAutor(),isbn.getPrefPais());
		return libroRepository.findByIsbn(isbnBuscado);
	}
	
   
	public Libro buscarById(Long id){
		 Libro buscado= libroRepository.findById(id).get();
		 return buscado;
	}
        
   
	public long contar() {
		return libroRepository.count();
	}
    
    public Long contarLibrosDisponibles() {
        return libroRepository.contarLibrosDisponibles();
    }
    
    public Long contarLibrosNoDisponibles() {
        return libroRepository.contarLibrosNoDisponibles();
    }

}
