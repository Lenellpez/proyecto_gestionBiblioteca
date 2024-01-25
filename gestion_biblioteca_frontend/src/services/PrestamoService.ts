import axios, { AxiosResponse } from "axios";

interface PrestamoDto {
  idLibro: number;
  idLector: number;
}

class PrestamoService {
    private readonly PRESTAMO_BASE_REST_API_URL = "http://localhost:9000/api/prestamos";
       
    getPrestamos(): Promise<AxiosResponse> {
        return axios.get(`${this.PRESTAMO_BASE_REST_API_URL}/`);
      }

      registrarDevolucion(id: number): Promise<AxiosResponse> {
        const url = `${this.PRESTAMO_BASE_REST_API_URL}/devolver/${id}`;
        return axios.post(url);
      }

      registrarPrestamo(prestamoDto:PrestamoDto): Promise<AxiosResponse> {
        const url = `${this.PRESTAMO_BASE_REST_API_URL}/prestar`;
        return axios.post(url, prestamoDto);
      }
  
}

export default new PrestamoService();
