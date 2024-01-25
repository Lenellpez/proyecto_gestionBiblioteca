
import React, { useEffect ,Fragment, useState} from 'react';
import { Combobox, Transition } from '@headlessui/react'
import { CheckIcon, ChevronUpDownIcon ,UsersIcon,BookOpenIcon} from '@heroicons/react/20/solid'
import LectorService from '../services/LectorService'
import LibroService from '../services/LibroService';
import PrestamoService from '../services/PrestamoService';
import MyModal from './modal';


export default  function addPrestamo () {
  
  const [selectedLector, setSelectedLector] = useState(null);
  const [selectedLibro, setSelectedLibro] = useState(null);
  const [lectorQuery, setLectorQuery] = useState('');
  const [libroQuery, setLibroQuery] = useState('');
  const [lectores, setLectores] = useState([]);
  const [libros, setLibros] = useState([]);

  const [isOpen, setIsOpen] = useState(false);   
  const [modalTitle, setModalTitle] = useState('');
  const [modalMessage, setModalMessage] = useState('');

    const closeModal = () => {
      setIsOpen(false);
    };

    const handleConfirmarPrestamo = () => {
    // Verificar si se han seleccionado un lector y un libro
    if (selectedLector && selectedLibro) {
      // Llamar al servicio PrestamoService para registrar el préstamo
      const prestamoDto = { idLector: selectedLector.id, idLibro: selectedLibro.id };
      PrestamoService.registrarPrestamo(prestamoDto)
      .then(response => {
        setModalTitle('Prestamo Registrado con Éxito!');
        setModalMessage(`El préstamo para ${selectedLector.nombre} ha sido registrado exitosamente.`, response);
        setIsOpen(true);
      })
      .catch(error => {
        console.error('Error al registrar el préstamo:', error);
      });
      } else {
        setModalTitle('Error en el prestamo');
        setModalMessage('Selecciona un lector y un libro antes de confirmar el préstamo.');
        setIsOpen(true);
      }

    };

  useEffect(() => {
    // Cargar los combobox options de lectores no multados al montar el componente
    LectorService.getLectoresByEstado('NO_MULTADO')
      .then(response => setLectores(response.data))
      .catch(error => console.error('Error al cargar lectores:', error));

      // Cargar los combobox options de libros no prestado al montar el componente
      LibroService.getLibrosByEstado('DISPONIBLE')
      .then(response => setLibros(response.data))
      .catch(error => console.error('Error al cargar libros:', error));
  }, []);

  const filteredLectores = lectorQuery === '' ? lectores : lectores.filter(lector =>
    lector.nombre.toLowerCase().includes(lectorQuery.toLowerCase())
  );

  const filteredLibros = libroQuery === '' ? libros : libros.filter(libro =>
    libro.titulo.toLowerCase().includes(libroQuery.toLowerCase())
  );

    return (
      <div className="flex flex-col items-center justify-center h-screen">
      <h1 className="text-2xl font-bold mb-4 text-gray-700">Registrar nuevo Prestamo</h1>
      <h2 className="text-sm mb-8 text-gray-500">Seleccionar un lector y un libro. 
      No se mostrarán aquellos lectores con multas pendientes o libros no disponibles</h2>
      <div className="w-full max-w-md md:flex md:space-x-4">
         {/* Primer ComboBox */}
      <Combobox value={selectedLector} onChange={setSelectedLector}>
        <div className="relative mt-1">
        <Combobox.Label> <UsersIcon className="h-6 w-6" /> </Combobox.Label>
          <div className="relative w-full cursor-default overflow-hidden rounded-lg bg-white text-left shadow-md focus:outline-none focus-visible:ring-2 focus-visible:ring-white/75 focus-visible:ring-offset-2 focus-visible:ring-offset-teal-300 sm:text-sm">
            <Combobox.Input
              className="w-full border-none py-2 pl-3 pr-10 text-sm leading-5 text-gray-900 focus:ring-0"
              displayValue={lector => lector ? lector.nombre : ''}
              onChange={(event) => setLectorQuery(event.target.value)}
            />
            <Combobox.Button className="absolute inset-y-0 right-0 flex items-center pr-2">
              <ChevronUpDownIcon
                className="h-5 w-5 text-gray-400"
                aria-hidden="true"
              />
            </Combobox.Button>
          </div>
          <Transition
            as={Fragment}
            leave="transition ease-in duration-100"
            leaveFrom="opacity-100"
            leaveTo="opacity-0"
            afterLeave={() => setLectorQuery('')}
          >
            <Combobox.Options className="absolute mt-1 max-h-60 w-full overflow-auto rounded-md bg-white py-1 text-base shadow-lg ring-1 ring-black/5 focus:outline-none sm:text-sm">
              {filteredLectores.length === 0 && lectorQuery !== '' ? (
                <div className="relative cursor-default select-none px-4 py-2 text-gray-700">
                  Sin resultados.
                </div>
              ) : (
                filteredLectores.map((lector) => (
                  <Combobox.Option
                    key={lector.id}
                    className={({ active }) =>
                      `relative cursor-default select-none py-2 pl-10 pr-4 ${
                        active ? 'bg-indigo-600 text-white' : 'text-gray-900'
                      }`
                    }
                    value={lector}
                  >
                    {({ selected, active }) => (
                      <>
                        <span
                          className={`block truncate ${
                            selected ? 'font-medium' : 'font-normal'
                          }`}
                        >
                          {lector.nombre}
                        </span>
                        {selected ? (
                          <span
                            className={`absolute inset-y-0 left-0 flex items-center pl-3 ${
                              active ? 'text-white' : 'text-teal-600'
                            }`}
                          >
                            <CheckIcon className="h-5 w-5" aria-hidden="true" />
                          </span>
                        ) : null}
                      </>
                    )}
                  </Combobox.Option>
                ))
              )}
            </Combobox.Options>
          </Transition>
        </div>
      </Combobox>

        {/* Segundo   ComboBox */}

      <Combobox value={selectedLibro} onChange={setSelectedLibro}>
        <div className="relative mt-1">
        <Combobox.Label> <BookOpenIcon className="h-6 w-6" /> </Combobox.Label>
          <div className="relative w-full cursor-default overflow-hidden rounded-lg bg-white text-left shadow-md focus:outline-none focus-visible:ring-2 focus-visible:ring-white/75 focus-visible:ring-offset-2 focus-visible:ring-offset-teal-300 sm:text-sm">
            <Combobox.Input
              className="w-full border-none py-2 pl-3 pr-10 text-sm leading-5 text-gray-900 focus:ring-0"
              displayValue={libro => libro ? libro.titulo : ''}
              onChange={(event) => setLibroQuery(event.target.value)}
            />
            <Combobox.Button className="absolute inset-y-0 right-0 flex items-center pr-2">
              <ChevronUpDownIcon
                className="h-5 w-5 text-gray-400"
                aria-hidden="true"
              />
            </Combobox.Button>
          </div>
          <Transition
            as={Fragment}
            leave="transition ease-in duration-100"
            leaveFrom="opacity-100"
            leaveTo="opacity-0"
            afterLeave={() => setLibroQuery('')}
          >
            <Combobox.Options className="absolute mt-1 max-h-60 w-full overflow-auto rounded-md bg-white py-1 text-base shadow-lg ring-1 ring-black/5 focus:outline-none sm:text-sm">
              {filteredLibros.length === 0 && setLibroQuery !== '' ? (
                <div className="relative cursor-default select-none px-4 py-2 text-gray-700">
                  Sin resultados.
                </div>
              ) : (
                filteredLibros.map((libro) => (
                  <Combobox.Option
                    key={libro.id}
                    className={({ active }) =>
                      `relative cursor-default select-none py-2 pl-10 pr-4 ${
                        active ? 'bg-indigo-600 text-white' : 'text-gray-900'
                      }`
                    }
                    value={libro}
                  >
                    {({ selected, active }) => (
                      <>
                        <span
                          className={`block truncate ${
                            selected ? 'font-medium' : 'font-normal'
                          }`}
                        >
                          {libro.titulo}
                        </span>
                        {selected ? (
                          <span
                            className={`absolute inset-y-0 left-0 flex items-center pl-3 ${
                              active ? 'text-white' : 'text-teal-600'
                            }`}
                          >
                            <CheckIcon className="h-5 w-5" aria-hidden="true" />
                          </span>
                        ) : null}
                      </>
                    )}
                  </Combobox.Option>
                ))
              )}
            </Combobox.Options>
          </Transition>
        </div>
      </Combobox>
        
      </div>
      <button className="bg-indigo-500 text-white py-2 px-4 mt-6 rounded-md hover:bg-indigo-600" onClick={handleConfirmarPrestamo}>
        Confirmar Préstamo
      </button>

      <MyModal isOpen={isOpen} closeModal={closeModal} title={modalTitle} message={modalMessage} />
    </div>
      );

}