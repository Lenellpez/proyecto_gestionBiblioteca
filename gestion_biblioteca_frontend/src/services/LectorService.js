import axios from "axios"
const LECTOR_BASE_REST_API_URL="http://localhost:9000/api/lectores/"

class LectorService {
      getAllLectorres(){
        return  axios.get(LECTOR_BASE_REST_API_URL + 'all');
      }

      getLectoresByEstado(estado){
        return axios.get(`${LECTOR_BASE_REST_API_URL}estado/${estado}`);
      }

      getLectoresByNombre(nombre){
        return axios.get(`${LECTOR_BASE_REST_API_URL}buscarLectoresByNombre/${nombre}`);
      }

    createLector(lector){
        return axios.post(LECTOR_BASE_REST_API_URL + 'lector', lector);
    }

    lectoresRegistrados(){
      return axios.post(LECTOR_BASE_REST_API_URL + 'lector', lector);
  }

  contarAsociados(){
    return axios.get(LECTOR_BASE_REST_API_URL +'contarNoSocios')
    .then(response => response.data)
    .catch(error => {
        console.error("Error al contar lectores asociados:", error);
        throw error;
    });;
  }

  contarNoAsociados(){
    return axios.get(LECTOR_BASE_REST_API_URL +'contarSocios')
    .then(response => response.data)
    .catch(error => {
        console.error("Error al contar lectores no asociados:", error);
        throw error;
    });;
  }

  eliminar(id) {
    return axios.delete(`${LECTOR_BASE_REST_API_URL}eliminar/${id}`);
  }

}
export default new LectorService ();

