import React , {useState,useEffect} from 'react'
import { Link } from 'react-router-dom'
import {PlusIcon,ClipboardDocumentListIcon} from '@heroicons/react/24/outline'
import ModalConfirmation from './modal-Confirmation'
import PrestamoService from '../services/PrestamoService'


 export default  function Readers () {
       // Estado para almacenar la lista de prestamos
        const [prestamos,setPrestamo]=useState([]); 
        // Estado para el texto de búsqueda 
        const [searchText, setSearchText] = useState('');  
        // Estado para el modal de confirmación
        const [modalOpen, setModalOpen] = useState(false);
        // Estado para el ID del prestamo a devolver 
        const [prestamoId, setPrestamoId] = useState(null);

        // Función para manejar el clic en el botón de devolver
        const handleReturnClick = (id) => {
          setModalOpen(true);
          setPrestamoId(id);
        };

        // Función para cancelar la devolucion del libro
        const handleCancelReturn = () => {
                setModalOpen(false);
                setPrestamoId(null);
        };

        // Función para confirmar la devolucion 
        const handleConfirmReturn = () => {
            PrestamoService.registrarDevolucion(prestamoId)
            .then(response => {
              // Lógica adicional después de registar la devolcion 
              console.log(response.data);
              setPrestamo(prestamos.filter(prestamo => prestamo.id !== prestamoId));
            })
            .catch(error => {
              // Lógica para manejar errores durante 
              console.log(error);
              console.error("Error en la solicitud Axios:", error.message);
            })
            .finally(() => {
              // Limpiar el estado después de manejar la eliminación
              setModalOpen(false);
              setPrestamoId(null);
            });
        };


       // Efecto para cargar todos los prestamos al montar el componente
        useEffect(()=>{
            PrestamoService.getPrestamos().then(response=>{
            setPrestamo(response.data);
          }).catch(error=>{
            console.log(error);
          })
        },[]);


          return (
      <div className="p-4 m-3 ">
            <div className=" flex flex-col lg:flex-row justify-between items-center mb-4">
              <div className="text-left lg:w-2/3 mb-4 lg:mb-0">
              <h2 className="text-lg font-semibold flex items-center">
                <span className="mr-2">Prestamos</span>
                <ClipboardDocumentListIcon className="h-6 w-6" />
              </h2>
                <p className="text-sm text-gray-500">
                  Listado de todos los prestamos registrados incluido el lector , libro y su estado 
                </p>
              </div>
              <div className="flex items-center space-x-2 lg:space-x-4">
              <input
                  type="text"
                 // value={searchText}
                 // onChange={handleSearch}
                  className="rounded-md border border-gray-300 px-3.5 py-2.5 text-sm focus:outline-none focus:border-indigo-600"
                  placeholder="Buscar lector"
                />
                <Link
                  to='/add-prestamos'
                  className="inline-block bg-indigo-600 hover:bg-indigo-500 focus:bg-indigo-500 focus:outline-none focus:ring focus:ring-indigo-300 focus:ring-opacity-50 transition-colors rounded-md px-4 py-2.5 text-sm font-semibold text-white shadow-sm"
                >
                  <div className="flex items-center">
                    <PlusIcon className="h-5 w-5 mr-2" />
                    <span>Nuevo Prestamo</span>
                  </div>
                </Link>
              </div>
            </div>
            <hr />
              <div className="overflow-hidden overflow-x-auto">
              {prestamos.length > 0 ? (
              <table className="min-w-full divide-y divide-gray-200">
                <thead>
                  <tr>
                  <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                      ID
                    </th>
                    <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                      Lector
                    </th>
                    <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                      Libro
                    </th>
                    <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                      Fecha del prestamo
                    </th>
                    <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                      Estado
                    </th>
                    <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                    Acciones
                  </th>
                  </tr>
                </thead>
                <tbody className="bg-white divide-y divide-gray-200">
                  {prestamos.map((prestamo) => (
                    <tr key={prestamo.id}>
                      <td className="px-6 py-4 whitespace-nowrap">{prestamo.id}</td>
                      <td className="px-6 py-4 whitespace-nowrap"> {prestamo.lector.nombre}</td>
                      <td className="px-6 py-4 whitespace-nowrap ">{prestamo.libro.titulo}</td>
                      <td className="px-6 py-4 whitespace-nowrap">{prestamo.fechaPrestamo}</td>
                      <td className={`px-6 py-4 whitespace-nowrap ${prestamo.estado === 'PRESTADO' ? 'text-red-500' : ''}`}>
                        {prestamo.estado === 'PRESTADO' ? 'Prestamo activo' : 'Devuelto'}
                      </td>
                      <td className="px-6 py-4 whitespace-nowrap">
                      {prestamo.estado === 'PRESTADO' && (
                        <button  onClick={() => handleReturnClick(prestamo.id)} className="font-medium text-indigo-600 hover:bg-indigo-100 px-2 py-1 rounded">Devolver</button>
                      )}
                      </td>
                    </tr>
                  ))}
                </tbody>
              </table> 
               ) : (
                <p className="text-center text-gray-500">No se encontraron resultados.</p>
              )}
              </div>

        <ModalConfirmation
        isOpen={modalOpen}
        closeModal={() => setModalOpen(false)}
        title="Confirmar Devolucion"
        message="¿Estás seguro de realizar la devolucion del libro?"
        onCancel={handleCancelReturn}
        onConfirm={handleConfirmReturn}
        />
      </div>
    )

   }