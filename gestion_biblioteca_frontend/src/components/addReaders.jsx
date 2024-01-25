import { useState } from 'react'
import { Link ,useNavigate} from 'react-router-dom';
import LectorService from '../services/LectorService';
import MyModal from './modal';
import { ArrowUturnLeftIcon} from '@heroicons/react/24/outline'

// Función de utilidad para generar clases condicionales
function classNames(...classes) {
  return classes.filter(Boolean).join(' ')
}

export default function AddReaders() {
  // Estados para manejar la información del formulario 
  const [nombre,setNombre]=useState('');
  const [apellido,setApellido]=useState('');
  const [telefono,setTelefono]=useState('');
  const [numero,setNumero]=useState(''); //el servidor se encargar de adm como cuil o nro socio 

  const [isOpen, setIsOpen] = useState(false);   // Estado para controlar la visibilidad de la modal
  const [modalTitle, setModalTitle] = useState('');
  const [modalMessage, setModalMessage] = useState('');

  const navigate = useNavigate ();
  const [agreed, setAgreed] = useState(false)
  const [readerType, setReaderType] = useState('ASC');

  // Función para manejar el cambio en el tipo de lector
  const handleReaderTypeChange = (event) => {
    setReaderType(event.target.value);
   // Limpiar los estados al cambiar el tipo de lector
   setNumero('');
  };
  // Función para cerrar la modal
  const closeModal = () => {
    setIsOpen(false);
  };
   // Función para guardar el lector
  const saveLector=(e)=>{
    e.preventDefault();
    const nombreCompleto = `${nombre} ${apellido}`;
    const lector ={tipo:readerType,nombre:nombreCompleto,telefono,numero};
    LectorService.createLector(lector).then((response)=>{
            setModalTitle('Carga exitosa');
            setModalMessage(response.data);
            setIsOpen(true);
            navigate('/lectores');
    }).catch(error=>{
        setModalTitle('Error al cargar');
        setModalMessage(
          error.response ? error.response.data : 'Hubo un problema al cargar el lector. Por favor, inténtalo de nuevo.'
        );
        setIsOpen(true);
    })
  }
// Estructura del componente JSX
  return (
    <div className=" bg-white px-6 py-24 sm:py-32 lg:px-8">
      <div
        className="absolute inset-x-0 top-[-10rem] -z-10 transform-gpu overflow-hidden blur-3xl sm:top-[-20rem]"
        aria-hidden="true"
      >
        <div
          className="relative left-1/2 -z-10 aspect-[1155/678] w-[36.125rem] max-w-none -translate-x-1/2 rotate-[30deg] bg-gradient-to-tr from-[#ff80b5] to-[#9089fc] opacity-30 sm:left-[calc(50%-40rem)] sm:w-[72.1875rem]"
          style={{
            clipPath:
              'polygon(74.1% 44.1%, 100% 61.6%, 97.5% 26.9%, 85.5% 0.1%, 80.7% 2%, 72.5% 32.5%, 60.2% 62.4%, 52.4% 68.1%, 47.5% 58.3%, 45.2% 34.5%, 27.5% 76.7%, 0.1% 64.9%, 17.9% 100%, 27.6% 76.8%, 76.1% 97.7%, 74.1% 44.1%)',
          }}
        />
      </div>
      <div className="mx-auto max-w-2xl text-center">
        <h2 className="text-3xl font-bold tracking-tight text-gray-900 sm:text-4xl">Carga de un nuevo lector</h2>
        <p className="mt-2 text-lg leading-8 text-gray-600">
          complete todos los campos
        </p>
      </div>
      <form  className="mx-auto mt-16 max-w-xl sm:mt-20">

        <div className="grid grid-cols-1 gap-x-8 gap-y-6 sm:grid-cols-2">
          <div>
            <label htmlFor="first-name" className="block text-sm font-semibold leading-6 text-gray-900">
              Nombre
            </label>
            <div className="mt-2.5">
              <input
                type="text"
                name="first-name"
                id="first-name"
                autoComplete="given-name"
                value={nombre}
                onChange={(e)=>setNombre(e.target.value)}
                className="block w-full rounded-md border-0 px-3.5 py-2 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"
              />
            </div>
          </div>

          <div>
            <label htmlFor="last-name" className="block text-sm font-semibold leading-6 text-gray-900">
               Apellido
            </label>
            <div className="mt-2.5">
              <input
                type="text"
                name="last-name"
                id="last-name"
                value={apellido}
                onChange={(e)=>setApellido(e.target.value)}
                autoComplete="family-name"
                className="block w-full rounded-md border-0 px-3.5 py-2 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"
              />
            </div>
          </div>

          <div className="sm:col-span-2">
          <label htmlFor="reader-type" className="block text-sm font-semibold leading-6 text-gray-900">
            Tipo de lector
          </label>
          <div className="mt-2.5">
            <select
              id="reader-type"
              name="reader-type"
              value={readerType}
              onChange={handleReaderTypeChange}  
              className="block w-full rounded-md border-0 px-3.5 py-2 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"
            >
              <option value="ASC">Asociado</option>
              <option value="NO_ASC">No Asociado</option>
            </select>
          </div>
        </div>

          <div className="sm:col-span-2">
            <label htmlFor="nroSocio" className="block text-sm font-semibold leading-6 text-gray-900">
              {readerType === 'ASC' ? 'Numero de socio' : 'Numero de CUIL'}
            </label>
            <div className="mt-2.5">
              <input
                type="text"
                name={readerType === 'ASC' ? 'nro_socio' : 'nro_cuil'}
                id={readerType === 'ASC' ? 'socio' : 'cuil'}
                autoComplete="organization"
                value={numero} 
                onChange={(e) => setNumero(e.target.value)}
                className="block w-full rounded-md border-0 px-3.5 py-2 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"
              />
            </div>
          </div>
     
          <div className="sm:col-span-2">
            <label htmlFor="phone-number" className="block text-sm font-semibold leading-6 text-gray-900">
              Telefono
            </label>
            <div className="mt-2.5">
              <input
                type="tel"
                name="phone-number"
                id="phone-number"
                value={telefono}
                onChange={(e)=>setTelefono(e.target.value)}
                autoComplete="tel"
                className="block w-full rounded-md border-0 px-3.5 py-2  text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"
              />
            </div>

          </div>
        </div>

        <div className="mt-10">
          <button
            type="submit"
            className="block w-full rounded-md bg-indigo-600 px-3.5 py-2.5 text-center text-sm font-semibold text-white shadow-sm hover:bg-indigo-500 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600"
            onClick={(e)=>saveLector(e)}
          >
            Guardar
          </button>
           <Link to='/lectores'className="block w-full mt-3 rounded-md bg-gray-400 px-3.5 py-2.5 text-sm font-semibold text-white shadow-sm hover:bg-gray-300 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600 flex items-center justify-center"><ArrowUturnLeftIcon className="h-4 w-4 mr-2" />Cacelar </Link>
        </div>
      </form>
      <MyModal isOpen={isOpen} closeModal={closeModal} title={modalTitle} message={modalMessage} />
    </div>
  )
}