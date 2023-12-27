import {DateTime} from "luxon";

export const prettyTime = (time) => {
    return DateTime.fromISO(time).toLocaleString()
}
