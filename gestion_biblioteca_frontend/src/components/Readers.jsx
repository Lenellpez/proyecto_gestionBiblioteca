import React , {useState,useEffect} from 'react'
import LectorService from '../services/LectorService'
import { Link } from 'react-router-dom'
import {PlusIcon,UsersIcon} from '@heroicons/react/24/outline'
import ModalConfirmation from './modal-Confirmation';

 export default  function Readers () {
       // Estado para almacenar la lista de lectores
        const [lectores,setLectores]=useState([]); 
        // Estado para el texto de búsqueda 
        const [searchText, setSearchText] = useState('');  
        // Estado para el modal de confirmación
        const [modalOpen, setModalOpen] = useState(false);
        // Estado para el ID del lector a eliminar
        const [lectorIdToDelete, setLectorIdToDelete] = useState(null);

        // Función para manejar el clic en el botón de eliminar
        const handleDeleteClick = (id) => {
          setModalOpen(true);
          setLectorIdToDelete(id);
        };

        // Función para cancelar la eliminación
        const handleCancelDelete = () => {
                setModalOpen(false);
              setLectorIdToDelete(null);
        };

        // Función para confirmar la eliminación del lector
        const handleConfirmDelete = () => {
          LectorService.eliminar(lectorIdToDelete)
            .then(response => {
              // Lógica adicional después de la eliminación exitosa
              console.log(response.data);
              setLectores(lectores.filter(lector => lector.id !== lectorIdToDelete));
            })
            .catch(error => {
              // Lógica para manejar errores durante la eliminación
              console.log(error);
              console.error("Error en la solicitud Axios:", error.message);
            })
            .finally(() => {
              // Limpiar el estado después de manejar la eliminación
              setModalOpen(false);
              setLectorIdToDelete(null);
            });
        };
         // Función para manejar el cambio en el campo de búsqueda
        const handleSearch = (e) => {
          const searchTextValue = e.target.value;
          setSearchText(searchTextValue);
       // Lógica para realizar la búsqueda con el texto ingresado (searchTextValue)
       if (searchTextValue.trim() === '') {
        // Si el campo de búsqueda está vacío, cargar todos los lectores
        LectorService.getAllLectorres()
          .then((response) => {
            setLectores(response.data);
          })
          .catch((error) => {
            console.log(error);
          });
      } else {
        // Si hay un valor en el campo de búsqueda, realizar la búsqueda
        LectorService.getLectoresByNombre(searchTextValue)
          .then((response) => {
            setLectores(response.data);
          })
          .catch((error) => {
            setLectores([]);
          });
      }
        };

       // Efecto para cargar todos los lectores al montar el componente
        useEffect(()=>{
          LectorService.getAllLectorres().then(response=>{
            setLectores(response.data);
          }).catch(error=>{
            console.log(error);
          })
        },[]);


          return (
      <div className="p-4 m-3 ">
            <div className=" flex flex-col lg:flex-row justify-between items-center mb-4">
              <div className="text-left lg:w-2/3 mb-4 lg:mb-0">
              <h2 className="text-lg font-semibold flex items-center">
                <span className="mr-2">Lectores</span>
                <UsersIcon className="h-6 w-6" />
              </h2>
                <p className="text-sm text-gray-500">
                  Lista todos los lectores incluido su nombre, teléfono, libros prestados y estado
                </p>
              </div>
              <div className="flex items-center space-x-2 lg:space-x-4">
              <input
                  type="text"
                  value={searchText}
                  onChange={handleSearch}
                  className="rounded-md border border-gray-300 px-3.5 py-2.5 text-sm focus:outline-none focus:border-indigo-600"
                  placeholder="Buscar lector"
                />
                <Link
                  to='/add-Lectores'
                  className="inline-block bg-indigo-600 hover:bg-indigo-500 focus:bg-indigo-500 focus:outline-none focus:ring focus:ring-indigo-300 focus:ring-opacity-50 transition-colors rounded-md px-4 py-2.5 text-sm font-semibold text-white shadow-sm"
                >
                  <div className="flex items-center">
                    <PlusIcon className="h-5 w-5 mr-2" />
                    <span>Agregar</span>
                  </div>
                </Link>
              </div>
            </div>
            <hr />
              <div className="overflow-hidden overflow-x-auto">
              {lectores.length > 0 ? (
              <table className="min-w-full divide-y divide-gray-200">
                <thead>
                  <tr>
                  <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                      ID
                    </th>
                    <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                      Nombre
                    </th>
                    <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                      Teléfono
                    </th>
                    <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                      Tipo
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
                  {lectores.map((person) => (
                    <tr key={person.id}>
                      <td className="px-6 py-4 whitespace-nowrap">{person.id}</td>
                      <td className="px-6 py-4 whitespace-nowrap"> {person.nombre}</td>
                      <td className="px-6 py-4 whitespace-nowrap ">{person.telefono}</td>
                      <td className="px-6 py-4 whitespace-nowrap">{person.nroSocio ? 'Asociado' : 'No Asociado'}</td>
                      <td className={`px-6 py-4 whitespace-nowrap ${person.estado === 'MULTADO' ? 'text-red-500' : ''}`}>
                        {person.estado === 'MULTADO' ? 'Multado' : 'No Multado'}
                      </td>
                      <td className="px-6 py-4 whitespace-nowrap">
                      <a href="" className="font-medium text-indigo-600 hover:bg-indigo-100 px-2 py-1 rounded">Editar</a>
                      <button  onClick={() => handleDeleteClick(person.id)} className="font-medium text-red-500 hover:bg-red-100 px-2 py-1 rounded">Eliminar</button>
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
        title="Confirmar eliminación"
        message="¿Estás seguro de que deseas eliminar este lector?"
        onCancel={handleCancelDelete}
        onConfirm={handleConfirmDelete}
        />
      </div>
    )

   }