import axios from "axios"
const LECTOR_BASE_REST_API_URL="http://localhost:9000/api/lectores/all"
const LECTOR_CARGA_REST_API_URL="http://localhost:9000/api/lectores/lector"

class LectorService {
      getAllLectorres(){
        return  axios.get(LECTOR_BASE_REST_API_URL);
      }

    createLector(lector){
        return axios.post(LECTOR_CARGA_REST_API_URL,lector);
    }

}
export default new LectorService ();

