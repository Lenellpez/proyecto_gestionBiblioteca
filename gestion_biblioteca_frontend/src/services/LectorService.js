import axios from "axios"
const LECTOR_BASE_REST_API_URL="http://localhost:9000/api/lectores/all"

class LectorService {
      getAllLectorres(){
        return  axios.get(LECTOR_BASE_REST_API_URL);
      }
}
export default new LectorService ();

