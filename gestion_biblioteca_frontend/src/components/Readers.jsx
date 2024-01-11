import React , {useState,useEffect} from 'react'
import LectorService from '../services/LectorService';
import { Link } from 'react-router-dom';

 export default  function Readers () {
        const [lectores,setLectores]=useState([]);    
        useEffect(()=>{
          LectorService.getAllLectorres().then(response=>{
            setLectores(response.data);
            console.log (response.data);
          }).catch(error=>{
            console.log(error);
          })
        },[]);
          return (
      <div className="p-4 m-3 ">
            <div className=" flex flex-col lg:flex-row justify-between items-center mb-4">
              <div className="text-left lg:w-2/3 mb-4 lg:mb-0">
                <h2 className="text-lg font-semibold">Lectores</h2>
                <p className="text-sm text-gray-500">
                  Lista todos los lectores incluido su nombre, teléfono, libros prestados y estado
                </p>
              </div>
              <div className="flex items-center space-x-2 lg:space-x-4">
                <button className="rounded-md bg-indigo-600 px-3.5 py-2.5 text-sm font-semibold text-white shadow-sm hover:bg-indigo-500 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600">
                  Buscar
                </button>
                <Link to='/add-Lectores' className="rounded-md bg-indigo-600 px-3.5 py-2.5 text-sm font-semibold text-white shadow-sm hover:bg-indigo-500 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600">Agregar lector</Link>
              </div>
            </div>
            <hr />
              <div className="overflow-hidden overflow-x-auto">
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
                        <a href="" className="font-medium text-indigo-600 hover:text-indigo-500">Editar</a>
                      </td>
                    </tr>
                  ))}
                </tbody>
              </table> 
              </div>
      </div>
    )

   }