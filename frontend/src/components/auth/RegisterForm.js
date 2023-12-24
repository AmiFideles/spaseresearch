import {useState} from "react";
import {useDispatch} from "react-redux";
import {Button, Form, Placeholder} from "react-bootstrap";
import {push} from "@lagunovsky/redux-react-router";

import {useRegisterMutation} from "../../api/authApi";
import LoadingButton from "../util/LoadingButton";
import {PasswordInput} from "../util/PasswordInput";
import {AlertBar} from "../util/AlertBar";
import {LoadError} from "../util/LoadError";
import {useGetProfessionsQuery} from "../../api/resourceApi";

export function RegisterForm() {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [firstName, setFirstName] = useState("");
    const [lastName, setLastName] = useState("");
    const [age, setAge] = useState("");
    const [gender, setGender] = useState("MALE");
    const [professions, setProfessions] = useState([]);

    const onUsernameChanged = (e) => setUsername(e.target.value);
    const onPasswordChanged = (e) => setPassword(e.target.value);
    const onGenderChanged = (e) => setGender(e.target.value);
    const onFirstNameChanged = (e) => setFirstName(e.target.value);
    const onLastNameChanged = (e) => setLastName(e.target.value);
    const onAgeChanged = (e) => setAge(e.target.value);
    const onProfessionsChanged = (e) => {
        const checked = e.target.checked;
        const id = e.target.value;
        if (checked) {
            setProfessions([...professions, id]);
        } else {
            setProfessions(professions.filter(el => el !== id));
        }
    }

    const dispatch = useDispatch()
    const [errorMessage, setErrorMessage] = useState("");
    const [showErrorMessage, setShowErrorMessage] = useState(false);
    const [register, {isLoading: registerIsLoading}] = useRegisterMutation()
    const {
        data: data,
        isLoading: professionIsLoading,
        isError: professionIsError,
    } = useGetProfessionsQuery()

    const handleSubmit = async (e) => {
        e.preventDefault()
        try {
            if (password.length < 6) {
                setErrorMessage("Password is too short!")
                setShowErrorMessage(true)
                return
            }
            await register({
                firstName: firstName,
                lastName: lastName,
                username: username,
                password: password,
                age: age,
                gender: gender,
                professions: professions
            }).unwrap()
            dispatch(push("/login"))
        } catch (err) {
            if (err.data && err.data.status) {
                setErrorMessage(err.data.message)
            } else {
                setErrorMessage("Unexpected error occurred!")
            }
            setShowErrorMessage(true)
        }
    }

    return (
        <Form style={{width: 400}}
              className="m-3 border border-1 shadow-lg rounded p-3"
              onSubmit={handleSubmit}
        >
            <AlertBar
                errorMessage={errorMessage}
                setShowErrorMessage={setShowErrorMessage}
                showErrorMessage={showErrorMessage}
            />
            <Form.Group className="mb-3">
                <Form.Label className="text-muted">Username</Form.Label>
                <Form.Control autoFocus required maxLength={255} type="text" name="username"
                              onChange={onUsernameChanged}/>
            </Form.Group>
            <Form.Group className="mb-3">
                <Form.Label className="text-muted">Password</Form.Label>
                <PasswordInput onChange={onPasswordChanged} value={password}/>
            </Form.Group>
            <Form.Group className="mb-3">
                <Form.Label className="text-muted">First name</Form.Label>
                <Form.Control autoFocus required maxLength={255} type="text" name="username"
                              onChange={onFirstNameChanged}/>
            </Form.Group>
            <Form.Group className="mb-3">
                <Form.Label className="text-muted">Last name</Form.Label>
                <Form.Control autoFocus required maxLength={255} type="text" name="username"
                              onChange={onLastNameChanged}/>
            </Form.Group>
            <Form.Group>
                <Form.Label className="text-muted">Gender</Form.Label>
                <div key={"gender-radio"} className="mb-3" onChange={onGenderChanged}>
                    <Form.Check
                        value="MALE"
                        name="gender-radio"
                        type="radio"
                        id="male-radio"
                        label="male"
                        defaultChecked={true}
                    />
                    <Form.Check
                        value="FEMALE"
                        name="gender-radio"
                        type="radio"
                        id="female-radio"
                        label="female"
                    />
                </div>
            </Form.Group>
            <Form.Group className={"mb-2"}>
                <Form.Label className="text-muted">Age</Form.Label>
                <Form.Control required maxLength={255} min={18} max={100} type="number" name="age"
                              onChange={onAgeChanged}/>
            </Form.Group>
            <Form.Group>
                <Form.Label className="text-muted">Professions</Form.Label>
                <div key={"inline-"} className="mb-3">
                    {professionIsError ? <LoadError/> :
                        professionIsLoading ? <Placeholder xs={6}/> :
                            data.map(el => (
                                <Form.Check
                                    key={el.id}
                                    onChange={onProfessionsChanged}
                                    label={el.name}
                                    value={el.id}
                                    name="profession-checkbox"
                                    type="checkbox"
                                />
                            ))
                    }
                </div>
            </Form.Group>
            <Form.Group>
                {registerIsLoading ?
                    <LoadingButton className="w-100" variant="success"/> :
                    <Button className="w-100" variant="success" type="submit"
                            disabled={username === "" || password === "" || firstName === "" || lastName === ""}>Sign
                        up</Button>
                }
            </Form.Group>
        </Form>
    )
}
