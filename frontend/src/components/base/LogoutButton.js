import {useDispatch} from "react-redux";
import {Button} from "react-bootstrap";
import {createLogoutThunk} from "../../redux/thunks/logoutThunk";

function LogoutButton() {
    const dispatch = useDispatch()

    const handleLogout = () => {
        dispatch(createLogoutThunk())
    }

    return (
        <Button variant="secondary" onClick={handleLogout}>Log out</Button>
    )
}

export default LogoutButton
