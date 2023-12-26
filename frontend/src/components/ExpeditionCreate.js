import {Link, useLocation} from "react-router-dom";
import {useMemo, useState} from "react";
import {NotFoundPage} from "./util/NotFoundPage";
import {Header} from "./base/Header";
import {Button, Form, Placeholder} from "react-bootstrap";
import {
    useCreateExpeditionMutation,
    useGetFreeResearchersQuery,
    useGetStationsQuery,
    useGetSuitableSpaceshipQuery
} from "../api/resourceApi";
import {useDispatch} from "react-redux";
import {push} from "@lagunovsky/redux-react-router";
import {StatusButton} from "./util/StatusButton";
import {LoadError} from "./util/LoadError";
import {StationDescription} from "./sub/StationDescription";
import {SpaceshipDescription} from "./sub/SpaceshipDescription";
import {ResearcherDescription} from "./sub/ResearcherDescription";

function useQuery() {
    const {search} = useLocation();
    return useMemo(() => new URLSearchParams(search), [search]);
}

export function ExpeditionCreate() {
    return (
        <div>
            <Header/>
            <div className="mt-3 container flex-column d-flex justify-content-center align-items-center">
                <div className="h3 text-center">
                    Create expedition
                </div>
                <ExpeditionCreateBody/>
            </div>
        </div>
    )
}

function ExpeditionCreateBody() {
    const query = useQuery();
    const step = query.get("step") ? query.get("step") : 1
    if (step == 1) {
        return <ExpeditionStepOne/>
    }
    const sourceStationId = query.get("sourceStationId");
    const destinationStationId = query.get("destinationStationId");
    if (step == 2 && sourceStationId && destinationStationId) {
        return <ExpeditionStepTwo sourceStationId={sourceStationId} destinationStationId={destinationStationId}/>
    }
    const spaceshipId = query.get("spaceshipId");
    if (step == 3 && sourceStationId && destinationStationId && spaceshipId) {
        return <ExpeditionStepThree spaceshipId={spaceshipId} sourceStationId={sourceStationId}
                                    destinationStationId={destinationStationId}/>
    }
    return <NotFoundPage/>
}

/*
список станций (отбытия, прибытия)
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
*/
function ExpeditionStepOne() {
    const [sourceStationId, setSourceStationId] = useState("")
    const [destinationStationId, setDestinationStationId] = useState("")
    const {data, isLoading, isError} = useGetStationsQuery()
    const handleClick = (e) => {
        const name = e.target.name;
        const id = e.target.value;
        if (name === "source-station-radio") {
            setSourceStationId(id);
        }
        if (name === "destination-station-radio") {
            setDestinationStationId(id);
        }
    }
    return (
        <div>
            <div>
                <h3>Stations</h3>
                {isError ? <LoadError/> : isLoading ? <Placeholder xs={6}/> :
                    <div className="mb-3 container" onChange={handleClick}>
                        {data.map(el =>
                            <div className="row" key={el.id}>
                                <StationDescription
                                    className="col-sm"
                                    station={el}
                                />
                                <Form.Check
                                    className="col-sm"
                                    value={el.id}
                                    name="source-station-radio"
                                    type="radio"
                                    label={"source"}
                                />
                                <Form.Check
                                    className="col-sm"
                                    value={el.id}
                                    name="destination-station-radio"
                                    type="radio"
                                    label={"destination"}
                                />
                            </div>)}
                    </div>
                }
            </div>
            {
                (!sourceStationId || !destinationStationId) ?
                    <Button variant="secondary" disabled>Choose stations</Button>
                    :
                    destinationStationId === sourceStationId
                        ?
                        <Button variant="danger" disabled>Wrong stations</Button>
                        :
                        <Button
                            variant="success"
                            as={Link}
                            to={`/expeditions/create?step=2&sourceStationId=${sourceStationId}&destinationStationId=${destinationStationId}`}
                        >
                            Next step
                        </Button>
            }
        </div>
    )
}

/*
список доступных кораблей (подходящих по расположению станции и типу планет)
- описание корабля
    - название
    - дата создания
    - производитель (название, страна)
    - максимальная скорость (км/c?)
    - вместимость (м^3?)
    - каюты
*/
function ExpeditionStepTwo({sourceStationId, destinationStationId}) {
    const [spaceshipId, setSpaceshipId] = useState("");
    const {data, isLoading, isError} = useGetSuitableSpaceshipQuery({sourceStationId, destinationStationId})
    const onSpaceshipChanged = (e) => setSpaceshipId(e.target.value);
    console.log(data);
    return (
        <div>
            <div>
                <h3>Spaceships</h3>
                {isError ? <LoadError/> : isLoading ? <Placeholder xs={6}/> :
                    <div key={"spaceship-radio"} className="mb-3 container" onChange={onSpaceshipChanged}>
                        {
                            data.map(spaceship =>
                                <div className="row" key={spaceship.id}>
                                    <SpaceshipDescription
                                        spaceship={spaceship}
                                        className="col-sm"
                                    />
                                    <Form.Check
                                        className="col-sm"
                                        value={spaceship.id}
                                        name="spaceship-radio"
                                        type="radio"
                                    />
                                </div>)
                        }
                    </div>
                }
            </div>
            {spaceshipId === ""
                ?
                <Button variant="secondary" disabled>Choose one</Button>
                :
                <Button
                    variant="success"
                    as={Link}
                    to={`/expeditions/create?step=3&sourceStationId=${sourceStationId}&destinationStationId=${destinationStationId}&spaceshipId=${spaceshipId}`}
                    disabled={spaceshipId === ""}
                >
                    Next step
                </Button>
            }
        </div>
    )
}

/*
список свободных участников
- описание участника
    - имя
    - фамилия
    - возраст
    - пол
    - профессии
*/
function ExpeditionStepThree({sourceStationId, destinationStationId, spaceshipId}) {
    const [participants, setParticipants] = useState([]);
    const {data, isLoading, isError} = useGetFreeResearchersQuery();
    const [createExpedition, {isLoading: sendIsLoading, isError: sendIsError}] = useCreateExpeditionMutation()
    const dispatch = useDispatch()
    const handleSubmit = async (e) => {
        e.preventDefault()
        try {
            await createExpedition({
                sourceStationId,
                destinationStationId,
                spaceshipId,
                participants,
            }).unwrap()
            dispatch(push(`/expeditions`))
        } catch (err) {
            console.log(err);
        }
    }
    const onParticipantChanged = (e) => {
        const checked = e.target.checked;
        const id = e.target.value;
        if (checked) {
            setParticipants([...participants, id]);
        } else {
            setParticipants(participants.filter(el => el !== id));
        }
    }
    return (
        <div>
            <div>
                <h3>Researchers</h3>
                {isError ? <LoadError/> : isLoading ? <Placeholder xs={6}/> :
                    <div className="mb-3">
                        {data.map(el => (
                            <div key={el.id}>
                                <ResearcherDescription data={el}/>
                                <Form.Check
                                    key={el.id}
                                    onChange={onParticipantChanged}
                                    value={el.id}
                                    name="participant-checkbox"
                                    type="checkbox"
                                />
                            </div>
                        ))}
                    </div>
                }
            </div>
            <StatusButton
                onClick={handleSubmit}
                isLoading={sendIsLoading}
                isError={sendIsError}
                className="w-100"
                variant="success"
                type="button"
            >
                Create
            </StatusButton>
        </div>
    )
}
