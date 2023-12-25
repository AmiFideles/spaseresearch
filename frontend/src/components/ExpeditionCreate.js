import {useLocation} from "react-router-dom";
import {useMemo, useState} from "react";

function useQuery() {
    const {search} = useLocation();
    return useMemo(() => new URLSearchParams(search), [search]);
}

const steps = [
    ExpeditionStepOne,
    ExpeditionStepTwo,
    ExpeditionStepThree
]

export function ExpeditionCreate() {
    const query = useQuery();
    const step = query.get("step") ? query.get("step") : 1
    return steps[step]
}

function ExpeditionStepOne() {
    const [sourceStationId, setSourceStationId] = useState("")
    const [destinationStationId, setDestinationStationId] = useState("")
    return (
        <div>
            Stations:
        </div>
    )
}

function ExpeditionStepTwo(sourceStationId, destinationStationId) {
    const [spaceshipId, setSpaceshipId] = useState("");
    return (
        <div>
            Source station: {sourceStationId} <br/>
            Destination station: {destinationStationId} <br/>
            Spaceships:
        </div>
    )
}

function ExpeditionStepThree(sourceStationId, destinationStationId, spaceshipId) {
    const [participants, setParticipants] = useState([]);
    return (
        <div>
            Source station: {sourceStationId} <br/>
            Destination station: {destinationStationId} <br/>
            Spaceship: {spaceshipId} <br/>
            Researchers:
        </div>
    )
}

/*
Создание экспедиции:
- список станций (отбытия, прибытия):
    - описание станции
        - название
        - дата создания
        - долгота
        - широта
    - описание планеты
        - название
        - радиус
        - тип
    - описание галактики
        - название
        - дата открытия
        - отдаленность от Земли
- список доступных кораблей (подходящих по расположению станции и типу планет):
    - название
    - дата создания
    - производитель (название, страна)
    - максимальная скорость (км/c?)
    - вместимость (м^3?)
    - каюты
- список свободных участников
    - имя
    - фамилия
    - профессии
    - возраст
    - пол
*/
