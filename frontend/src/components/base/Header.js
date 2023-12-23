import {Form, Navbar} from "react-bootstrap";
import AuthButton from "./AuthButton";

function Header() {
    return (
        <Navbar bg="light" variant="light" className="d-flex flex-row justify-content-center flex-wrap">
            <Navbar.Brand className="m-1" href="/">Space Researchers</Navbar.Brand>
            <div className="d-flex flex-row w-75 justify-content-evenly flex-wrap"></div>
            <Form inline="true" className="">
                <AuthButton/>
            </Form>
        </Navbar>
    )
}

export default Header
