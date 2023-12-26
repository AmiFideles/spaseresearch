import {Button, Form, Navbar} from "react-bootstrap";
import AuthButton from "./AuthButton";
import {Link} from "react-router-dom";
import {useSelector} from "react-redux";
import {selectIsCapitan} from "../../redux/login/loginSelectors";

export function Header() {
    const isCapitan = useSelector(selectIsCapitan);
    console.log(isCapitan);
    return (
        <Navbar bg="light" variant="light" className="d-flex flex-row justify-content-center flex-wrap">
            <Navbar.Brand className="m-1" href="/">Space Researchers</Navbar.Brand>
            <div className="d-flex flex-row w-75 justify-content-evenly flex-wrap"></div>
            {isCapitan ? <Button className="m-1" variant="success" as={Link} to={`/expeditions/create`}>Create expedition</Button> : ""}
            <Form inline="true" className="">
                <AuthButton/>
            </Form>
        </Navbar>
    )
}
