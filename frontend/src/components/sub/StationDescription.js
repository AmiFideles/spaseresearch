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
import {prettyTime} from "../util/pretty";

export function StationDescription({station}) {
    const planet = station.planet;
    const galaxy = planet.galaxy;
    return (
        <div>
            <h4>Station: {station.name}</h4>
            <div>Latitude: {station.latitude}</div>
            <div>Longitude: {station.longitude}</div>
            <div>Opening Date: {prettyTime(station.openingDate)}</div>
            <div>
                <h5>Planet:</h5>
                <div>Name: {planet.name}</div>
                <div>Type: {planet.planetType.name}</div>
                <div>Radius: {planet.radius}</div>
                <div>
                    <h6>Galaxy:</h6>
                    <div>Name: {galaxy.name}</div>
                    <div>Discovery Data: {prettyTime(galaxy.discoveryDate)}</div>
                    <div>Remote Distance: {galaxy.remoteDistance}</div>
                </div>
            </div>
        </div>
    )
}
