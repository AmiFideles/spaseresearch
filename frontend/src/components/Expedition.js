import {useParams} from "react-router-dom";

export function Expedition() {
    let { id } = useParams();
    return (
        <div>
            Expedition ID: {id}
        </div>
    )
}
