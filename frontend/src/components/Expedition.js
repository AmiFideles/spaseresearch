import {useParams} from "react-router-dom";
import {useGetExpeditionQuery} from "../api/resourceApi";
import {LoadError} from "./util/LoadError";
import {Placeholder} from "react-bootstrap";

export function Expedition() {
    let { id } = useParams();
    const {data, isLoading, isError} = useGetExpeditionQuery(id);
    console.log(data);
    return (
        <div>
            {isError ? <LoadError/> : isLoading ? <Placeholder xs={6}/> :
                <div>{`Expedition ID: ${data}`}</div>
            }
        </div>
    )
}
