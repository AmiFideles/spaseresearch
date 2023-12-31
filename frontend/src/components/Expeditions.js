import {Link} from "react-router-dom";
import {Button, Placeholder, Table} from "react-bootstrap";
import {useGetExpeditionsQuery} from "../api/resourceApi";
import {LoadError} from "./util/LoadError";
import {Header} from "./base/Header";
import {prettyTime} from "./util/pretty";

export function Expeditions() {
    const {data, isLoading, isError} = useGetExpeditionsQuery()
    return (
        <div className="vh-100">
            <Header/>
            <div className="mt-3 container flex-column d-flex justify-content-center align-items-center">
                <div className="h3 text-center">
                    Your Expeditions
                </div>
                {isError ? <LoadError/> : isLoading ? <Placeholder xs={6}/> :
                    <Table striped borderless hover className="m-0 text-center">
                        <thead style={{position: "sticky", top: "0", backgroundColor: "white"}}>
                        <tr>
                            <th>Name</th>
                            <th>Status</th>
                            <th>Start</th>
                            <th>Finish</th>
                            <th></th>
                        </tr>
                        </thead>
                        <tbody>
                        {
                            data.length !== 0 ?
                                data.map(el => (
                                    <tr key={el.id}>
                                        <td>Expedition #{el.id}</td>
                                        <td>{el.status}</td>
                                        <td>{prettyTime(el.departureTime)}</td>
                                        <td>{el.endTime ? prettyTime(el.endTime) : "Not yet"}</td>
                                        <td><Button variant="success" as={Link} to={`/expeditions/${el.id}`}>More</Button></td>
                                    </tr>
                                )) :
                                null
                        }
                        </tbody>
                    </Table>}
            </div>
        </div>
    )
}
