import Header from "./base/Header";
import {Placeholder, Table} from "react-bootstrap";
import {useGetExpeditionsQuery} from "../api/resourceApi";
import {LoadError} from "./util/LoadError";

export function Expeditions() {
    const {data, isLoading, isError} = useGetExpeditionsQuery()

    return (
        <div className="vh-100">
            <Header/>
            <div className="mt-3 container flex-column d-flex justify-content-center align-items-center">
                <div className="h3 text-center">
                    Your Expeditions
                </div>
                <Table striped borderless hover className="m-0">
                    <thead style={{position: "sticky", top: "0", backgroundColor: "white"}}>
                    <tr className={"text-center"}>
                        <th>Name</th>
                        <th>Status</th>
                        <th>Start</th>
                        <th>Finish</th>
                        <th>Button</th>
                    </tr>
                    </thead>
                    <tbody>
                    {isError ? <LoadError/> :
                        isLoading ? <Placeholder xs={6}/> :
                            data.length !== 0 ?
                                data.map(el => (
                                    <tr key={el.id}>
                                        <td>{el.id}</td>
                                    </tr>
                                )) :
                                null
                    }
                    </tbody>
                </Table>
            </div>
        </div>
    )
}
