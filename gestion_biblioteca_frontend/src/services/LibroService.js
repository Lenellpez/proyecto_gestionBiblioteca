import axios from "axios"
const LIBRO_BASE_REST_API_URL="http://localhost:9000/api/libro/"

class LibroService {

    getLibros(){
        return  axios.get(LIBRO_BASE_REST_API_URL);
      }

      createLector(libro){
        return axios.post(LIBRO_BASE_REST_API_URL,libro);
      }


}

export default new LibroService();