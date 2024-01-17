import axios from "axios"
const LECTOR_BASE_REST_API_URL="http://localhost:9000/api/lectores/"

class LectorService {
      getAllLectorres(){
        return  axios.get(LECTOR_BASE_REST_API_URL + 'all');
      }

    createLector(lector){
        return axios.post(LECTOR_BASE_REST_API_URL + 'lector', lector);
    }

    lectoresRegistrados(){
      return axios.post(LECTOR_BASE_REST_API_URL + 'lector', lector);
  }

  contarLectores(){
    return axios.get(LECTOR_BASE_REST_API_UR +'contarAll')
    .then(response => response.data)
    .catch(error => {
        console.error("Error al contar lectores:", error);
        throw error;
    });;
  }

}
export default new LectorService ();

