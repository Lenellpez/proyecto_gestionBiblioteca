package ar.edu.unju.fi.Biblioteca.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ar.edu.unju.fi.Biblioteca.dto.PrestamoDto;
import ar.edu.unju.fi.Biblioteca.exceptions.ManagerException;
import ar.edu.unju.fi.Biblioteca.model.Prestamo;
import ar.edu.unju.fi.Biblioteca.service.PrestamoService;

@RestController
@RequestMapping("/api/prestamos")
public class PrestamoController {
	
	 @Autowired
	 private PrestamoService prestamoService;

	  @GetMapping("/") 
		public ResponseEntity<List<Prestamo>> getAll(){
			List<Prestamo> lectores = prestamoService.getAll();
			return ResponseEntity.ok(lectores);
		}

		@PostMapping("/prestar")
		public ResponseEntity<?> registraPrestamo(@RequestBody PrestamoDto prestamoDto) {
			String mensaje;
			try {
				prestamoService.realizarPrestamo(prestamoDto);
				mensaje = "Prestamo registrado correctamente.";
				return ResponseEntity.ok(mensaje);
			} catch (ManagerException e) {
				mensaje = e.getMessage();
				return ResponseEntity.internalServerError().body(mensaje);
			}
		}

	    
		@PostMapping("/devolver/{id}")
		public ResponseEntity<?> registrarDevolucion(@PathVariable("id") Long id) {
			prestamoService.realizarDevolucion(id);
			String mensaje = "Devolucion resgistrada correctamente.";
			return ResponseEntity.ok(mensaje);
		}

		
		@GetMapping("/{id}")
		public ResponseEntity<Prestamo> obtener(@PathVariable("id") Long id) {
			Prestamo prestamo = prestamoService.buscarId(id);
			System.out.println(prestamo);
			return ResponseEntity.ok(prestamo);
		}
		
		
		


}
