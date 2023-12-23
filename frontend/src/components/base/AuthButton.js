import React from "react";

import LogoutButton from "./LogoutButton";
import LoginButton from "./LoginButton";

import {selectToken} from "../../redux/login/loginSelectors";
import {useSelector} from "react-redux";

function AuthButton() {
    const token = useSelector(selectToken)

    if (token === null) {
        return (
            <LoginButton />
        )
    } else {
        return (
            <LogoutButton />
        )
    }
}

export default AuthButton
