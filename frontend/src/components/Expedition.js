import {Link, useParams} from "react-router-dom";
import {useGetExpeditionQuery} from "../api/resourceApi";
import {LoadError} from "./util/LoadError";
import {Button, Placeholder} from "react-bootstrap";
import {Header} from "./base/Header";

/*
Описание Экспедиции:
Если >= IN PROGRESS
- название
- статус
- дата начала
- id станций -> кнопки
- id корабля -> кнопка
- список описания участников (пометить, кто из них - командир)
Если LANDED
- отчет
Если COMPLETED
- дата конца

TODO:
!!!Если командир:
Если IN PROGRESS:
    - добавить кнопку для перехода на экран создания отчета
Если LANDED:
    - кнопку завершения экспедиции
*/
export function Expedition() {
    const {id} = useParams();
    const {data, isLoading, isError} = useGetExpeditionQuery(id);
    console.log(data);
    return (
        <div>
            <Header/>
            <div className="mt-3 container flex-column d-flex justify-content-center align-items-center">
                <div className="h3 text-center">
                    Expedition
                </div>
                {isError ? <LoadError/> : isLoading ? <Placeholder xs={6}/> : ExpeditionDescription(data)}
            </div>
        </div>
    )
}

function ExpeditionDescription(data) {
    return (
        <div>
            <h4>{`Expedition #${data.id}`}</h4>
            <div>{`Status: ${data.status}`}</div>
            <div>{`Departure time: ${data.departureTime}`}</div>
            {data.endTime ? <div>{`End time: ${data.endTime}`}</div> : ""}
            <div>
                {`Source station: ${data.sourceStation.name}`}
                <Button variant="success" as={Link} to={`/stations/${data.sourceStation.id}`}>></Button>
            </div>
            <div>
                {`Destination station: ${data.destinationStation.name}`}
                <Button variant="success" as={Link} to={`/stations/${data.destinationStation.id}`}>></Button>
            </div>
            <div>
                {`Spaceship: ${data.spaceship.name}`}
                <Button variant="success" as={Link} to={`/spaceships/${data.spaceship.id}`}>></Button>
            </div>
            <ReportDescription data={data.report} />
            <div>
                Participants
                {data.participants.map(el => <ResearcherDescription key={el.id} data={el} />)}
            </div>
        </div>
    )
}

function ResearcherDescription({data}) {
    return (
        <div>
            <h5>Researcher #{data.id}</h5>
            <div>First name: {data.firstName}</div>
            <div>Last name: {data.lastName}</div>
            <div>Gender: {data.gender}</div>
            <div>Age: {data.age}</div>
            {data.isCapitan ? <div>Capitan</div> : ""}
            <h6>Professions</h6>
            {data.professions.map(el => <div key={el.id}>{el.name}</div>)}
        </div>
    )
}

/*
Описание отчета:
- список поломок
*/
export function ReportDescription({data}) {
    return (
        <div>
            <h5>Report #{data.id}</h5>
            <div>
                {data.breakdowns.map(el => <div key={el.id}>{el.name}</div>)}
            </div>
        </div>
    )
}
