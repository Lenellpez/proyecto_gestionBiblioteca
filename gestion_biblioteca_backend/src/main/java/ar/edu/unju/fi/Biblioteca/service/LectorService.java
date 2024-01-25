package ar.edu.unju.fi.Biblioteca.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ar.edu.unju.fi.Biblioteca.dto.LectorDto;
import ar.edu.unju.fi.Biblioteca.enums.EstadoLector;
import ar.edu.unju.fi.Biblioteca.exceptions.ManagerException;
import ar.edu.unju.fi.Biblioteca.model.Asociado;
import ar.edu.unju.fi.Biblioteca.model.Lector;
import ar.edu.unju.fi.Biblioteca.model.NoAsociado;
import ar.edu.unju.fi.Biblioteca.repository.ILectorRepository;

@Service
public class LectorService {

	@Autowired
	private ILectorRepository lectorRepository;
	
    /**
     * Guardar un nuevo lector basado en los datos proporcionados en un DTO.
     *  @param lectorDto: DTO con los datos del lector.
     */
    public void gestionLector(LectorDto l)  {
    	
        if (l == null || l.getTipo() == null || l.getNombre() == null || l.getTelefono() == null || l.getNumero() == null) {
            throw new IllegalArgumentException("Todos los campos son obligatorios.");
        }
    	  //uso expresiones ternarias para crear instancias de Asociado o NoAsociado según el valor de tipo en LectorDto
    	   Lector lector = "ASC".equalsIgnoreCase(l.getTipo()) ?
                   new Asociado(l.getNombre(), l.getTelefono(), EstadoLector.NO_MULTADO, l.getNumero()) :
                   "NO_ASC".equalsIgnoreCase(l.getTipo()) ?
                           new NoAsociado(l.getNombre(), l.getTelefono(), EstadoLector.NO_MULTADO, l.getNumero()) :
                           null;
               lectorRepository.save(lector); 
    }
    
    public void actualizarLector(Long id , LectorDto lectorActualizado) throws ManagerException {
    	  Lector lector=buscarLectorById(id);
    	  lector.setNombre(lectorActualizado.getNombre());
    	  lector.setTelefono(lectorActualizado.getTelefono());
    }
    
    public String  eliminarLector(Long id) throws ManagerException {
        Lector lector = buscarLectorById(id);
        String nombreLector = lector.getNombre(); // Obtener el nombre antes de eliminar
        lectorRepository.delete(lector);
        return nombreLector;
    }
    
    /**
     * listar todos los lectores 
     * @return lista lectores 
     */
	public List<Lector> getAll(){
		List<Lector> lectores = new ArrayList<>();
		lectorRepository.findAll().forEach(lectores::add);
		return lectores;
	}
	
	 /**
     *  buscar lectores segun su estado (MULTADO|NO_MULTADO)
     * @return lista lectores 
     */
	public List<Lector> findByEstado(EstadoLector estado ){
		return lectorRepository.findByEstado(estado);
	}
	
	/**
    * listar todos los asociados
    * @return lista lectores asociados
    */
	public List<Asociado> getAsociados(){
		List<Lector> lectores = getAll();
       return getAsociadoFrom(lectores);
	}
	
	/**
	 * listar todos los no asociados
	 * @return lista lectores no asociados
	*/
	public List<NoAsociado> getNoAsociados(){
		List<Lector> lectores = getAll();
       return getNoAsociadoFrom(lectores);
	}
	
	
	/**
	* filtra una lista de lectores, seleccionando solo aquellos que son instancias de la clase Asociado
	*  @param lista de todos los lectores
	*  @return lista asociados 
	*  
	*/
	private List <Asociado> getAsociadoFrom (List <Lector> lectores){
		List<Asociado> asociados= new ArrayList <>();
		for(Lector lector : lectores) {
			if(lector instanceof Asociado) {
				asociados.add((Asociado)lector); // casting para convertir el objeto lector a un objeto de tipo Asociado
			}
		}
		return asociados;
	}
	
	/**
	 * filtra una lista de lectores, seleccionando solo aquellos que son instancias de la clase NoAsociado
	 * @param lista de todos los lectores
	 * @return lista no asociados 
	 */
	private List <NoAsociado> getNoAsociadoFrom (List <Lector> lectores){
		List<NoAsociado> Noasociados= new ArrayList <>();
		for(Lector lector : lectores) {
			if(lector instanceof NoAsociado) {
				Noasociados.add((NoAsociado)lector); // casting para convertir el objeto lector a un objeto de tipo No Asociado
			}
		}
		return Noasociados;
	}

    
    /**
     * busca lectores por nombre 
     * @param nombre completo del lector a buscar
     * @return lista lectores encontrados
     */
    public List<Lector> buscarLectoresByNombre(String nombre) {
        List<Lector> resultados = lectorRepository.findByNombreContainingIgnoreCase(nombre);
        return resultados;
    }
    
    
    /**
     * busca lectores por id 
     * @param id  del lector a buscar
     * @return lector encotrado
     * @throws ManagerException si el id no concide con ninguno registrado
     */
    public Lector buscarLectorById(Long id) throws ManagerException{
         return lectorRepository.findById(id)
                 .orElseThrow(() -> new ManagerException("El ID del lector no existe"));
    }
    
	/**
	 * Contar la cantidad total de Lectores registrados.
	 * @return Cantidad de lectores
	 */
	public long contar() {
		return lectorRepository.count();
	}
    
	/**
	 * Contar la cantidad total de Lectores asociados.
	 * @return Cantidad de socios
	 */
	public long contarAsociados() {
		return lectorRepository.contarAsociados();
	}
	
	/**
	 * Contar la cantidad total de Lectores NO asociados.
	 * @return Cantidad de no socios
	 */
	public long contarNoAsociados() {
		return lectorRepository.contarNoAsociados();
	}
	
	
}
