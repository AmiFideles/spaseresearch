/*
Описание Корабля:
- Название
- Дата создания
- Производитель (название, страна)
- Максимальная скорость (км/c?)
- Вместимость (м^3?)
- Каюты, отображать к каким имеет доступ текущий пользователь
*/
export function SpaceshipDescription({spaceship}) {
    const manufacturer = spaceship.manufacturer;
    return (
        <div>
            <h5>Spaceship: {spaceship.name}</h5>
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
