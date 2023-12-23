import {Route, Routes} from "react-router-dom";
import {ErrorPage} from "./components/NotFoundPage";
import "bootstrap/dist/css/bootstrap.min.css"
import "bootstrap-icons/font/bootstrap-icons.css"
import UnprotectedComponent from "./components/base/UnprotectedComponent";
import {Login} from "./components/auth/Login";
import {Register} from "./components/auth/Register";

function App() {
    return (
        <Routes>
            <Route path="/register" element={<UnprotectedComponent><Register/></UnprotectedComponent>}/>
            <Route path="/login" element={<UnprotectedComponent><Login/></UnprotectedComponent>}/>
            <Route path="*" element={<ErrorPage/>}/>
        </Routes>
    )
}

export default App;
