package ar.edu.unju.fi.Biblioteca;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ar.edu.unju.fi.Biblioteca.dto.IsbnDto;
import ar.edu.unju.fi.Biblioteca.dto.LibroDto;
import ar.edu.unju.fi.Biblioteca.enums.TipoLibro;
import ar.edu.unju.fi.Biblioteca.model.Libro;
import ar.edu.unju.fi.Biblioteca.service.LibroService;

@SpringBootTest
public class LibroTest {

	   @Autowired
	   LibroService libroService; 
  

	    @BeforeEach
	    public void setUp() {
	    	LibroDto libro1 = new LibroDto(1998,"Norwegian Wood",TipoLibro.NOVELA,1L,"Vintage Books",123, 456,456 ,101);
	    	LibroDto libro2 = new LibroDto(2002,"Pride and Prejudice",TipoLibro.TEATRO,1L,"Sudamericana",123, 456,456 ,101);
			libroService.guardarLibro(libro1);
	    	libroService.guardarLibro(libro2);
	     }
	    
	    @Test 
	    @DisplayName("traer todos los libros")
	    void test1(){
 	     // Obtener la lista de libros después de las operaciones
	        List<Libro> libros = libroService.getAll();
	        //  verificar que las operaciones fueron exitosas
	        assertEquals(2, libros.size());
	    }
	    
	    
	    @Test 
	    @DisplayName("Buscar por su nombre")
	    void test2(){
	    	List<Libro> libros = libroService.getLibrosByTitulo("Norwegian Wood");
	    	assertEquals(1, libros.size());
	    }
	    
	  	    
	    @Test
	    @DisplayName("buscar por su isbn")
	    void test3() {
	        // Crear un ISBN para buscar
	        IsbnDto isbnABuscar = new IsbnDto (123, 456, 789, 101);
	        // Ejecutar el método que se está probando
	        Libro resultado = libroService.buscarIsbn(isbnABuscar);
	        // Verificar que se encontró el libro esperado en los resultados
	        assertNotNull(resultado);
	   
	    } 
	    
}
