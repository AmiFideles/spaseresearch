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
import {Spaceship} from "./components/Spaceship";
import {Station} from "./components/Station";
import {ExpeditionCreate} from "./components/ExpeditionCreate";
import {ReportCreate} from "./components/ReportCreate";


const globalStyles = {
    backgroundImage: "url('./images/image2.jpg')",
    backgroundSize: 'cover',
    backgroundPosition: 'center',
    backgroundRepeat: 'no-repeat',
};

function App() {
    return (
        <div style={globalStyles}>
            <Routes >
                <Route path="/" element={<ProtectedComponent><Expeditions/></ProtectedComponent>}/>
                <Route path="/expeditions/:id" element={<ProtectedComponent><Expedition/></ProtectedComponent>}/>
                <Route path="/expeditions/create" element={<ProtectedComponent><ExpeditionCreate/></ProtectedComponent>}/>
                <Route path="/spaceships/:id" element={<ProtectedComponent><Spaceship/></ProtectedComponent>}/>
                <Route path="/stations/:id" element={<ProtectedComponent><Station/></ProtectedComponent>}/>
                <Route path="/reports/create/:id" element={<ProtectedComponent><ReportCreate/></ProtectedComponent>}/>
                <Route path="/register" element={<UnprotectedComponent><Register/></UnprotectedComponent>}/>
                <Route path="/login" element={<UnprotectedComponent><Login/></UnprotectedComponent>}/>
                <Route path="*" element={<NotFoundPage/>}/>
            </Routes>
        </div>

    )
}

export default App;
