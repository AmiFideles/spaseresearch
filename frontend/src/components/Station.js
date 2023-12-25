import {Placeholder} from "react-bootstrap";
import {useParams} from "react-router-dom";
import {useGetStationQuery} from "../api/resourceApi";
import {Header} from "./base/Header";
import {LoadError} from "./util/LoadError";

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

/*
- название
- дата создания
- долгота
- широта
- планета:
    - название
    - радиус
    - тип
    - галактика:
        - название
        - дата открытия
        - отдаленность от Земли
*/
function StationDescription({station}) {
    const planet = station.planet;
    const galaxy = planet.galaxy;
    return (
        <div>
            <h4>Name: {station.name}</h4>
            <div>Latitude: {station.latitude}</div>
            <div>Longitude: {station.longitude}</div>
            <div>Opening Date: {station.openingDate}</div>
            <div>
                <h5>Planet:</h5>
                <div>Name: {planet.name}</div>
                <div>Type: {planet.planetType.name}</div>
                <div>Radius: {planet.radius}</div>
                <div>
                    <h6>Galaxy:</h6>
                    <div>Name: {galaxy.name}</div>
                    <div>Discovery Data: {galaxy.discoveryDate}</div>
                    <div>Remote Distance: {galaxy.remoteDistance}</div>
                </div>
            </div>
        </div>
    )
}
