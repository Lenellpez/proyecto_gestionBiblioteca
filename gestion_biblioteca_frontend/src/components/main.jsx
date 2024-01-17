import {BrowserRouter,Route, Routes } from "react-router-dom"
import Readers from "./Readers"
import Header from "./header"
import Nav from "./nav"
import AddReaders from "./addReaders"
import Books from "./books"

const main = () =>{
   return(
       <BrowserRouter>
       <div >
        <Nav/>
         <Routes>
         <Route exact path='/' element={<Header/>} > </Route>
         <Route  path='/lectores' element={<Readers/>} > </Route>
         <Route path='/add-Lectores' element={<AddReaders/>} > </Route>
         <Route path='/libros' element={<Books/>}></Route>
         </Routes>
       </div>
       </BrowserRouter>
   )
}

export default main