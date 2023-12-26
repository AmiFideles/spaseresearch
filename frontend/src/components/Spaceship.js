import {Placeholder} from "react-bootstrap";
import {useParams} from "react-router-dom";
import {useGetSpaceshipQuery} from "../api/resourceApi";
import {Header} from "./base/Header";
import {LoadError} from "./util/LoadError";
import {SpaceshipDescription} from "./sub/SpaceshipDescription";

export function Spaceship() {
    const {id} = useParams();
    const {data, isLoading, isError} = useGetSpaceshipQuery(id);
    return (
        <div>
            <Header/>
            <div className="mt-3 container flex-column d-flex justify-content-center align-items-center">
                <div className="h3 text-center">Spaceship</div>
                {isError ? <LoadError/> : isLoading ? <Placeholder xs={6}/> : <SpaceshipDescription spaceship={data}/>}
            </div>
        </div>
    )
}
