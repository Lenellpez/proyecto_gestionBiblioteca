package ar.edu.unju.fi.Biblioteca;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import ar.edu.unju.fi.Biblioteca.enums.EstadoLector;
import ar.edu.unju.fi.Biblioteca.model.Asociado;
import ar.edu.unju.fi.Biblioteca.model.Lector;
import ar.edu.unju.fi.Biblioteca.model.NoAsociado;
import ar.edu.unju.fi.Biblioteca.service.LectorService;


@SpringBootTest
public class LectorTest {
	
   @Autowired
   LectorService lectorService; 
   
   @BeforeEach
   public void setup(){
     //cargar un lectro asociado y no asociado 
       Asociado asociado1 = new Asociado( "Juan", "123456789", EstadoLector.NO_MULTADO, 123);
       NoAsociado noAsociado1 = new NoAsociado( "Ana", "987654321", EstadoLector.NO_MULTADO, 456); 
       lectorService.gestionLector(asociado1);
       lectorService.gestionLector(noAsociado1);
   }
   
   @Test
   @DisplayName("listar todos los lectores ")
   void test1 () {
       // Buscar lectores por nombre
       List<Lector> resultados = lectorService.getAll();  
       // Verificar que se encuentre los 2 lectores 
       assertEquals(2, resultados.size());
       assertEquals("Juan", resultados.get(0).getNombre());
   }
   
   
   @Test
   @DisplayName("Buscar lector por Id")
   void tes2() {
       // Verificar que el lector se haya guardado correctamente
       Lector lectorGuardado = lectorService.buscarLectorById(1L);
       assertNotNull(lectorGuardado);
       assertEquals("Juan", lectorGuardado.getNombre());
   }
   
   
   @Test
   @DisplayName("Buscar lector asociado por nombre")
   void test3 () {
       // Buscar lectores por nombre
       List<Lector> resultados = lectorService.buscarLectoresByNombre("Juan");   
       // Verificar que se encuentre el lector esperado en los resultados
       assertEquals(1, resultados.size());
       assertEquals("Juan", resultados.get(0).getNombre());
   }
   
   
   @Test
   @DisplayName("actualizar lector")
   void test4 () {
	   Lector noAsociado = lectorService.buscarLectorById(2L);   
       noAsociado.setEstado(EstadoLector.MULTADO);
       lectorService.gestionLector(noAsociado);
       assertEquals(EstadoLector.MULTADO, lectorService.buscarLectorById(4L).getEstado());
   }
   

}
