import {Placeholder} from "react-bootstrap";
import {useParams} from "react-router-dom";
import {useGetSpaceshipQuery} from "../api/resourceApi";
import {Header} from "./base/Header";
import {LoadError} from "./util/LoadError";

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

/*
Описание Корабля:
- Название
- Дата создания
- Производитель (название, страна)
- Максимальная скорость (км/c?)
- Вместимость (м^3?)
- Каюты, отображать к каким имеет доступ текущий пользователь
*/
function SpaceshipDescription({spaceship}) {
    const manufacturer = spaceship.manufacturer;
    return (
        <div>
            <div>Name: {spaceship.name}</div>
            <div>Date of construction: {spaceship.dateOfConstruction}</div>
            <div>Max speed: {spaceship.maxSpeed}</div>
            <div>Capacity: {spaceship.capacity}</div>
            <div>In expedition: {spaceship.inExpedition ? "Yes" : "No"}</div>
            <div>
                <h6>Manufacturer:</h6>
                <div>Name: {manufacturer.name}</div>
                <div>Country: {manufacturer.country}</div>
            </div>
            <div>
                <h6>Planet types</h6>
                {spaceship.planetTypes.map(type => <div key={type.id}>{type.name}</div>)}
            </div>
            <div>
                <h6>Cabins</h6>
                {spaceship.cabins.map(cabin => (
                    <div key={cabin.id}>
                        <div>Name: {cabin.name}</div>
                        <div>Volume: {cabin.volumeCubicMeters} m^3</div>
                        <div>Limited access?: {cabin.limitedAccess ? "Yes" : "No"}</div>
                        <div>You have access?: {cabin.limitedAccess && !cabin.currentHasAccess ? "No" : "Yes"}</div>
                    </div>
                ))}
            </div>
        </div>
    );
}
