import {BrowserRouter,Route, Routes } from "react-router-dom"
import Readers from "./Readers"
import Header from "./header"
import Nav from "./nav"
import AddReaders from "./addReaders"

const main = () =>{
   return(
       <BrowserRouter>
       <div >
        <Nav/>
         <Routes>
         <Route exact path='/' element={<Header/>} > </Route>
         <Route  path='/lectores' element={<Readers/>} > </Route>
         <Route path='/add-Lectores' element={<AddReaders/>} > </Route>
         </Routes>
       </div>
       </BrowserRouter>
   )
}

export default main