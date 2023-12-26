import {Placeholder} from "react-bootstrap";
import {useParams} from "react-router-dom";
import {useGetStationQuery} from "../api/resourceApi";
import {Header} from "./base/Header";
import {LoadError} from "./util/LoadError";
import {StationDescription} from "./sub/StationDescription";

export function Station() {
    const {id} = useParams();
    const {data, isLoading, isError} = useGetStationQuery(id);
    return (
        <div>
            <Header/>
            <div className="mt-3 container flex-column d-flex justify-content-center align-items-center">
                <div className="h3 text-center">
                    Station
                </div>
                <div>
                    {isError ? <LoadError/> : isLoading ? <Placeholder xs={6}/> : <StationDescription station={data}/>}
                </div>
            </div>
        </div>
    )
}
