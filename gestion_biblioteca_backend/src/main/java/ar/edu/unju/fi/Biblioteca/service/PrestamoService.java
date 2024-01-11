package ar.edu.unju.fi.Biblioteca.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.Biblioteca.dto.PrestamoDto;
import ar.edu.unju.fi.Biblioteca.enums.EstadoLector;
import ar.edu.unju.fi.Biblioteca.enums.EstadoLibro;
import ar.edu.unju.fi.Biblioteca.enums.EstadoPrestamo;
import ar.edu.unju.fi.Biblioteca.exceptions.ManagerException;
import ar.edu.unju.fi.Biblioteca.model.Lector;
import ar.edu.unju.fi.Biblioteca.model.Libro;
import ar.edu.unju.fi.Biblioteca.model.Prestamo;
import ar.edu.unju.fi.Biblioteca.repository.ILectorRepository;
import ar.edu.unju.fi.Biblioteca.repository.ILibroRepository;
import ar.edu.unju.fi.Biblioteca.repository.IPrestamoRepository;
import ar.edu.unju.fi.Biblioteca.util.FechaUtil;

@Service
public class PrestamoService {

	@Autowired
	private IPrestamoRepository prestamoRepository;
	
	@Autowired
	private ILibroRepository libroRepository;
	
	@Autowired
	private ILectorRepository lectorRepository;
	
	/**
	 * lista todo los prestamos registrados
	 * @return lista prestamos 
	 */
	public List<Prestamo> getAll(){
		List<Prestamo> prestamos = new ArrayList<>();
		prestamoRepository.findAll().forEach(prestamos::add);
	    return prestamos;
	}
	
	public long contar() {
		return prestamoRepository.count();
	}
	
	/**
	 * Buscar un préstamo por ID.
	 * @param id ID del préstamo
	 * @return Préstamo buscado
	 */
	public Prestamo buscarId(Long id) {
		return prestamoRepository.findById(id).get();
	}
	
    /**
     * realizar un nuevo préstamo de un libro para un lector 
     * @param Id de lector y libro contenidas en dto
     * @throws ManagerException si el lector no cumple con alguna de las validaciones se emitirá una excepción
     */
    public void realizarPrestamo(PrestamoDto prestamoDto) throws ManagerException {
        	Lector lector =lectorRepository.findById(prestamoDto.getIdLector()).get();
            Libro libro = libroRepository.findById(prestamoDto.getIdLibro()).get();
            validarLibroDisponible(libro);         
            validarLimiteLibros(lector);
            validarMulta(lector);       
            validarPosiblesMultas(lector);
            Prestamo prestamo = new Prestamo(lector, libro, LocalDate.now(), LocalDate.now().plusDays(lector.getMaxDiasPrestamo()), EstadoPrestamo.PRESTADO);
            prestamoRepository.save(prestamo);
            cambiarEstadoLibro(libro,EstadoLibro.PRESTADO);
    }
    
    /* Realizar la devolución de un libro 
     * @param idPrestamo ID del préstamo
    */
    public void realizarDevolucion(Long idPrestamo){
         	Prestamo prestamo  =  prestamoRepository.findById(idPrestamo).get();
            prestamo.setEstado(EstadoPrestamo.DEVUELTO);
            prestamo.setFechaDevolucion(LocalDate.now());
            cambiarEstadoLibro(prestamo.getLibro(),EstadoLibro.DISPONIBLE);  
            multarLector(prestamo);
      	    prestamoRepository.save(prestamo);   //guardar prestamos actualizados
    }

    /* multa al lector en caso de devolver fuera de termino 
     * @param idPrestamo ID del préstamo 
    */
    public void multarLector(Prestamo prestamo) {
        //cargar multa al lector
   	   if (FechaUtil.diasTranscurridos(prestamo.getFechaPrestamo(), LocalDate.now() ) > prestamo.getLector().getMaxDiasPrestamo()) {
             prestamo.getLector().setEstado(EstadoLector.MULTADO);
             lectorRepository.save(prestamo.getLector());
         }
    }
    
    /**
     * Obtene  los  préstamos activos de lector
     * @return lista prestamos activos 
     */
 	public List<Prestamo> getPrestamosActivos(Lector lector) {
 	    return prestamoRepository.findByLectorAndEstado(lector, EstadoPrestamo.PRESTADO);
 	}
 	    
     public void cambiarEstadoLibro(Libro libro,EstadoLibro nuevoEstado) {
     	libro.setEstado(nuevoEstado);
 		libroRepository.save(libro);
     }

     /* VALIDACIONES :*/
     
    public void validarLibroDisponible(Libro libro) throws ManagerException {
        if (libro.isEstado()==EstadoLibro.PRESTADO) {
            throw new ManagerException("El libro " + libro.getTitulo()  +" no está disponible para el préstamo.");
        }
    }
    
    /*
     *validar si un lector ha alcanzado el límite de prestamos
    */
   public void validarLimiteLibros(Lector lector) throws ManagerException {
	   long totalPrestActivos =prestamoRepository.countByLectorAndEstado(lector, EstadoPrestamo.PRESTADO);
        if (lector.getMaxLibrosPrestamo() == totalPrestActivos) {
            throw new ManagerException("El lector ha alcanzado el límite de "+totalPrestActivos+" libros préstados.");
        }
    }
     

    public void validarMulta(Lector lector)  throws ManagerException {   
    	if (lector.getEstado().equals(EstadoLector.MULTADO)) {
	    	throw new ManagerException("El lector tiene multa no se puede realizar el prestamo");
	    }
    }

    /**
     *prestamo activos cuya fecha de devolicion expiro
     */
    public void validarPosiblesMultas(Lector lector) {
    	List<Prestamo> todosLosPrestamos = prestamoRepository.findByLectorAndEstado(lector, EstadoPrestamo.PRESTADO);
    	for (Prestamo prestamo : todosLosPrestamos) {
    		  if (FechaUtil.diasTranscurridos(prestamo.getFechaPrestamo(),LocalDate.now()) > lector.getMaxDiasPrestamo()) {
    			  throw new ManagerException("Error: el lector podría enfrentar una multa ya que ha excedido el límite de días de préstamo para un libro que tiene en su posesión.");
    		 }
    	}
    }
    
}
