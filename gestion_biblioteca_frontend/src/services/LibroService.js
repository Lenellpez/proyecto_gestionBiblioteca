import axios from "axios"
const LIBRO_BASE_REST_API_URL="http://localhost:9000/api/libro/"

class LibroService {

    getLibros(){
        return  axios.get(LIBRO_BASE_REST_API_URL);
      }

      getLibrosByEstado(estado){
        return  axios.get(`${LIBRO_BASE_REST_API_URL}estado/${estado}`);
      }

      createLector(libro){
        return axios.post(LIBRO_BASE_REST_API_URL,libro);
      }

      async contarLibrosDisponibles(){
        try {
          const response = await axios.get(LIBRO_BASE_REST_API_URL + 'contarLibrosDisponibles');
          return response.data;
        } catch (error) {
          console.error("Error al contar libros disponibles:", error);
          throw error;
        };
      }

      async contarLibrosNoDisponibles(){
        try {
          const response = await axios.get(LIBRO_BASE_REST_API_URL + 'contarLibrosPrestados');
          return response.data;
        } catch (error) {
          console.error("Error al contar libros prestados:", error);
          throw error;
        };
      }

}

export default new LibroService();