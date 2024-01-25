import LibroService from '../services/LibroService';
import React , {useState,useEffect} from 'react'
import { Link } from 'react-router-dom';
import {PlusIcon,BookOpenIcon} from '@heroicons/react/24/outline'


export default  function Books (){
    const [libros,setLibros]=useState([]);    
    useEffect(()=>{
        LibroService.getLibros().then(response=>{
        setLibros(response.data);
        console.log (response.data);
      }).catch(error=>{
        console.log(error);
      })
    },[]);

 return(
    <div className="p-4 m-3 ">
    <div className=" flex flex-col lg:flex-row justify-between items-center mb-4">
      <div className="text-left lg:w-2/3 mb-4 lg:mb-0">
      <h2 className="text-lg font-semibold flex items-center">
                <span className="mr-2">Libros</span>
                <BookOpenIcon className="h-6 w-6" />
              </h2>
        <p className="text-sm text-gray-500">
          Lista todos los libros incluido su nombre, titulo y estado 
        </p>
      </div>
      <div className="flex items-center space-x-2 lg:space-x-4">
                <input
                  type="text"
                //  value={searchText}
                 // onChange={handleSearch}
                  className="rounded-md border border-gray-300 px-3.5 py-2.5 text-sm focus:outline-none focus:border-indigo-600"
                  placeholder="Buscar libros"
                />
               <Link
                  to='#'
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
        {libros.length > 0 ? (
      <table className="min-w-full divide-y divide-gray-200">
        <thead>
          <tr>
          <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
              ID
            </th>
            <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
              Titulo
            </th>
            <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
              Editorial
            </th>
            <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
              Autor
            </th>
            <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
              Tipo
            </th>
            <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
              Año
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
          {libros.map((libro) => (
            <tr key={libro.id}>
              <td className="px-6 py-4 whitespace-nowrap">{libro.id}</td>
              <td className="px-6 py-4 whitespace-nowrap"> {libro.titulo}</td>
              <td className="px-6 py-4 whitespace-nowrap ">{libro.aditorial}</td>
              <td className="px-6 py-4 whitespace-nowrap ">{libro.autor.nombre}</td>
              <td className="px-6 py-4 whitespace-nowrap">{libro.tipo }</td>
              <td className="px-6 py-4 whitespace-nowrap">{libro.anio }</td>
              <td className={`px-6 py-4 whitespace-nowrap ${libro.estado === 'PRESTADO' ? 'text-red-500' : ''}`}>
                {libro.estado === 'PRESTADO' ? 'Prestado' : 'Disponible'}
              </td>
              <td className="px-6 py-4 whitespace-nowrap">
                <a href="" className="font-medium text-indigo-600 hover:bg-indigo-100 px-2 py-1 rounded">Editar</a>
                <a href="" className="font-medium text-red-500 hover:bg-red-100 px-2 py-1 rounded">Eliminar</a>
              </td>
            </tr>
          ))}
        </tbody>
      </table> 
        ):(
          <p className="text-center text-gray-500">No se encontraron resultados.</p>
        )}
      </div>
</div>
 )
}