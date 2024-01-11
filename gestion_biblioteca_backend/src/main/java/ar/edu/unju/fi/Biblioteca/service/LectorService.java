package ar.edu.unju.fi.Biblioteca.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
     * Guardar o actualizar  nuevo lector
     * @param lector : lector con todos lus datos
     */
    public void gestionLector(Lector lector)  {
    	lectorRepository.save(lector);
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
     * @return lector buscado
     * @throws ManagerException si el id del lector buscado no concide con ninguno registrado
     */
    public Lector buscarLectorById(Long id) throws ManagerException{
         Lector lector = lectorRepository.findById(id).get();      
         if (lector!=null) {
             return lector;
         } else {
             // Puedes manejar el caso en el que no se encuentra el lector, por ejemplo, lanzar una excepción
        	 throw new ManagerException ("El ID del lector no existe");
         }
    }
    
	/**
	 * Contar la cantidad total de Lectores registrados.
	 * @return Cantidad de lectores
	 */
	public long contar() {
		return lectorRepository.count();
	}
    
	
}
