package ar.edu.unju.fi.Biblioteca;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ar.edu.unju.fi.Biblioteca.dto.PrestamoDto;
import ar.edu.unju.fi.Biblioteca.enums.EstadoLector;
import ar.edu.unju.fi.Biblioteca.enums.EstadoLibro;
import ar.edu.unju.fi.Biblioteca.exceptions.ManagerException;
import ar.edu.unju.fi.Biblioteca.model.Libro;
import ar.edu.unju.fi.Biblioteca.model.Prestamo;
import ar.edu.unju.fi.Biblioteca.service.LectorService;
import ar.edu.unju.fi.Biblioteca.service.LibroService;
import ar.edu.unju.fi.Biblioteca.service.PrestamoService;

@SpringBootTest
public class PrestamoTest {
   
	@Autowired
	PrestamoService prestamoService;
	
	@Autowired
	LibroService libroService;
	
	@Autowired
	LectorService lectorService;
	
	 @BeforeEach
	 public void setUp() {
		  
	      assertEquals(EstadoLibro.DISPONIBLE, libroService.buscarById(1L).getEstado());	// Verifica estado libro DISPONIBLE
	      PrestamoDto prestamoDto=new PrestamoDto();
	      prestamoDto.setIdLector(5L);
	      prestamoDto.setIdLibro(1L);
	      prestamoService.realizarPrestamo(prestamoDto);
	      assertEquals(2, prestamoService.contar());		
	      assertEquals(EstadoLibro.PRESTADO, libroService.buscarById(1L).getEstado());	// verificar el cambio de estado del libro 
	 }
	
	 @Test
	 @DisplayName("Validar carga prestamos")
	 public void test1() {
		 List<Prestamo> prestamos = prestamoService.getAll();
		 assertEquals(2, prestamos.size());
	 }
	 
	 
	 
	 @Test
	 @DisplayName("solicitar un libro no disponible")
	 public void test2() {
		 Libro libro = libroService.buscarById(2L);
	     libro.setEstado(EstadoLibro.PRESTADO);
	     libroService.actualizar(libro);
	     
	      PrestamoDto prestamoDto=new PrestamoDto();
	      prestamoDto.setIdLector(1L);
	      prestamoDto.setIdLibro(2L);
	     
		  	assertThrows(ManagerException.class, () -> {
				prestamoService.realizarPrestamo(prestamoDto);
			});
	 }
	 
	 
	 @Test
	 @DisplayName("devolver libro ")
	 public void test3() {
		 assertEquals(EstadoLibro.PRESTADO,libroService.buscarById(1L).getEstado());
		 prestamoService.realizarDevolucion(2L);
		 assertEquals(EstadoLibro.DISPONIBLE,libroService.buscarById(1L).getEstado()); //actualizacion estado de libro al ser devuelto 
	 }
	 
	 
	 @Test
	 @DisplayName("realizar multa a un lector  ")
	 public void test4() {
		 assertEquals(EstadoLector.NO_MULTADO,lectorService.buscarLectorById(1L).getEstado());
		 prestamoService.realizarDevolucion(1L);
		 assertEquals(EstadoLector.MULTADO,lectorService.buscarLectorById(1L)); 
	 }
	 
	 
	 
}
