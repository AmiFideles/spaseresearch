import "bootstrap/dist/css/bootstrap.min.css"
import "bootstrap-icons/font/bootstrap-icons.css"
import {Route, Routes} from "react-router-dom";

import UnprotectedComponent from "./components/base/UnprotectedComponent";
import ProtectedComponent from "./components/base/ProtectedComponent";
import {Login} from "./components/auth/Login";
import {Register} from "./components/auth/Register";
import {NotFoundPage} from "./components/util/NotFoundPage";
import {Expeditions} from "./components/Expeditions";
import {Expedition} from "./components/Expedition";

function App() {
    return (
        <Routes>
            <Route path="/" element={<ProtectedComponent><Expeditions/></ProtectedComponent>}/>
            <Route path="/expeditions/:id" element={<ProtectedComponent><Expedition/></ProtectedComponent>}/>
            <Route path="/register" element={<UnprotectedComponent><Register/></UnprotectedComponent>}/>
            <Route path="/login" element={<UnprotectedComponent><Login/></UnprotectedComponent>}/>
            <Route path="*" element={<NotFoundPage/>}/>
        </Routes>
    )
}

export default App;
