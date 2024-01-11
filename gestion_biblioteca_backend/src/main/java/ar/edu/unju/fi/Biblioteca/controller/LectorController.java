package ar.edu.unju.fi.Biblioteca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.unju.fi.Biblioteca.exceptions.ManagerException;
import ar.edu.unju.fi.Biblioteca.model.Asociado;
import ar.edu.unju.fi.Biblioteca.model.Lector;
import ar.edu.unju.fi.Biblioteca.model.NoAsociado;
import ar.edu.unju.fi.Biblioteca.service.LectorService;


@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/lectores")
public class LectorController {
   
	 @Autowired
	  private LectorService lectorService;
    
	 @GetMapping("/all")
	 public ResponseEntity<List<Lector>>getAll(){
		 List<Lector> lectores = lectorService.getAll();
		 return ResponseEntity.ok(lectores);
	 }
	 
	 
	
	@GetMapping("/{tipoLector}") 
	public ResponseEntity<List<?>> getAsociado (@PathVariable("tipoLector") String tipoLector){
		List<?> lectores = null;
		if(tipoLector.equalsIgnoreCase("Asociado")) {
			 lectores =lectorService.getAsociados();
		}
		if(tipoLector.equalsIgnoreCase("NoAsociado")) {
			 lectores =lectorService.getNoAsociados();
		}		
		return ResponseEntity.ok(lectores);
	}
	  
	  // Endpoint para crear un nuevo asociado
    @PostMapping("/asociado")
    public ResponseEntity<?> crearLector(@RequestBody Asociado asociado ) {
        try {
        	lectorService.gestionLector(asociado);
            String mensaje = "El Lector Asociado se Guardo Correctemente";
			return ResponseEntity.ok(mensaje);
        } catch (ManagerException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
	  // Endpoint para crear un nuevo no lector
    @PostMapping("/noAsociado")
    public ResponseEntity<?> crearLector(@RequestBody NoAsociado noAsociado ) {
        try {
        	lectorService.gestionLector(noAsociado);
            String mensaje = "El Lector No asociado se Guardo Correctemente";
			return ResponseEntity.ok(mensaje);
        } catch (ManagerException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    // Endpoint para buscar lectores por nombre
    @GetMapping("/buscarLectoresByNombre/{nombre}")
    public ResponseEntity<List<Lector>> buscarLectoresByNombre(@PathVariable("nombre") String nombre) {
        List<Lector> lectores = lectorService.buscarLectoresByNombre(nombre);
        if (!lectores.isEmpty()) {
            return new ResponseEntity<>(lectores, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    
    // Endpoint para buscar lectores por id
    @GetMapping("/id/{id}")
    public ResponseEntity<Lector> buscarLectorById(@PathVariable Long id) {
    	Lector lector = lectorService.buscarLectorById(id);
        if (lector != null) {
            return new ResponseEntity<>(lector, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/contarAll")
    public ResponseEntity<Long> contarLectores() {
        long cantidadLectores = lectorService.contar();
        return ResponseEntity.ok(cantidadLectores);
    }
    
}