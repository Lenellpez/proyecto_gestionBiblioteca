package ar.edu.unju.fi.Biblioteca.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import ar.edu.unju.fi.Biblioteca.dto.IsbnDto;
import ar.edu.unju.fi.Biblioteca.dto.LibroDto;
import ar.edu.unju.fi.Biblioteca.exceptions.ManagerException;
import ar.edu.unju.fi.Biblioteca.model.Libro;
import ar.edu.unju.fi.Biblioteca.service.LibroService;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/libro")
public class LibroController {
	 
	  @Autowired
	  LibroService libroService;
	 
	  
		 @GetMapping("/")
		 public ResponseEntity<List<Libro>> getAll(){
			List<Libro> libros = libroService.getAll();
			return ResponseEntity.ok(libros);
		 }
		 
	  
	    @PostMapping("/")
	    public ResponseEntity<String> crearLibro(@RequestBody LibroDto libroDto) {
	        try {        
	        	libroService.guardarLibro(libroDto);
	            return new ResponseEntity<>("Libro guardado correctamente", HttpStatus.CREATED);
	         }catch (ManagerException e) {
	            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	        }
	    }
	    
	    // Endpoint para buscar libros por título
	    @GetMapping("/titulo/{titulo}")
	    public ResponseEntity<List<Libro>> buscarLibrosByTitulo(@PathVariable("titulo") String titulo) {
	        List<Libro> libros = libroService.getLibrosByTitulo(titulo);
	        if (!libros.isEmpty()) {
	            return new ResponseEntity<>(libros, HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    } 
	    
	    // Endpoint para buscar libros por autor 
	    @GetMapping("/autor/{autor}")
	    public ResponseEntity<List<Libro>> buscarLibrosByAutor(@PathVariable("autor") String autor) {
	        List<Libro> libros = libroService.getLibrosByAutor(autor);
	        if (!libros.isEmpty()) {
	            return new ResponseEntity<>(libros, HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    } 
	    
		@GetMapping("/buscarIsbn/{isbn}")
		public ResponseEntity<Libro> buscarPorIsbn(@RequestBody IsbnDto isbn) {
			Libro libroBuscado = libroService.buscarIsbn(isbn);
			return ResponseEntity.ok(libroBuscado);
		}

		
	    @GetMapping("/contarLibrosDisponibles")
	    public ResponseEntity<Long> contarLibrosDisponibles() {
	        Long cantidadLibrosDisponibles = libroService.contarLibrosDisponibles();
	        return ResponseEntity.ok(cantidadLibrosDisponibles);
	    }

	    @GetMapping("/contarLibrosPrestados")
	    public ResponseEntity<Long> contarLibrosPrestados() {
	        Long cantidadLibrosPrestados = libroService.contarLibrosNoDisponibles();
	        return ResponseEntity.ok(cantidadLibrosPrestados);
	    }
	    
	    @GetMapping("/contarAll")
	    public ResponseEntity<Long> contarLectores() {
	        long cantidadLibros = libroService.contar();
	        return ResponseEntity.ok(cantidadLibros);
	    }
	    
	    
}
