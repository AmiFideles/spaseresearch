import {useState} from "react";
import {useParams} from "react-router-dom";
import {useDispatch} from "react-redux";
import {Form, Placeholder} from "react-bootstrap";
import {push} from "@lagunovsky/redux-react-router";
import {useCreateReportMutation, useGetBreakdownsQuery} from "../api/resourceApi";
import {Header} from "./base/Header";
import {LoadError} from "./util/LoadError";
import {StatusButton} from "./util/StatusButton";

export function ReportCreate() {
    const {id} = useParams();
    const {data, isLoading, isError} = useGetBreakdownsQuery();
    const [breakdowns, setBreakdowns] = useState([]);
    const onCheckboxClicked = (e) => {
        const checked = e.target.checked;
        const id = e.target.value;
        const name = e.target.name;
        console.log(breakdowns)
        if (checked) {
            setBreakdowns([...breakdowns, {id, name}]);
        } else {
            setBreakdowns(breakdowns.filter(el => el.id !== id));
        }
    }
    const [createReport, {isLoading: sendIsLoading, isError: sendIsError}] = useCreateReportMutation()
    const dispatch = useDispatch()
    const handleSubmit = async (e) => {
        e.preventDefault()
        try {
            await createReport({
                expeditionId: id,
                breakdowns: breakdowns,
            }).unwrap()
            dispatch(push(`/expeditions/${id}`))
        } catch (err) {
            console.log(err);
        }
    }

    return (
        <div>
            <Header/>
            <div className="mt-3 container flex-column d-flex justify-content-center">
                <div className="h3 text-center">
                    Create report for expedition {id}
                </div>
                <Form onSubmit={handleSubmit}>
                    <Form.Group className="mb-3">
                        <Form.Label className="text-muted">Breakdowns</Form.Label>
                        {isError ? <LoadError/> : isLoading ? <Placeholder xs={6}/> :
                            <div key={"inline-"} className="mb-3">
                                {data.map(el => (
                                    <Form.Check
                                        key={el.id}
                                        onChange={onCheckboxClicked}
                                        label={el.name}
                                        value={el.id}
                                        name={el.name}
                                        type="checkbox"
                                    />
                                ))}
                            </div>
                        }
                    </Form.Group>
                    <Form.Group>
                        <StatusButton
                            isLoading={sendIsLoading}
                            isError={sendIsError}
                            className="w-100"
                            variant="success"
                            type="submit"
                        >
                            Create
                        </StatusButton>
                    </Form.Group>
                </Form>
            </div>
        </div>
    )
}
