import React from 'react'
import ReactDOM from 'react-dom/client'
import App from './App.jsx'
import './index.css'

/*
*router :gestionar la navegación en aplicaciones React
 axion :entercomunicador back y fron 
 cors  :mecanismo de seguridad permite controlar las peticiones http
*/

ReactDOM.createRoot(document.getElementById('root')).render(
  <React.StrictMode>
    <App />
  </React.StrictMode>,
)
