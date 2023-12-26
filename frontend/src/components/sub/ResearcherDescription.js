/*
Описание Пользователя:
- Имя
- Фамилия
- Возраст
- Пол
- Является капитаном (Да/Нет)
- Список профессий?
*/
export function ResearcherDescription({data}) {
    const isCapitan = !!data.isCapitan
    const name = isCapitan ? "Capitan" : "Researcher"
    return (
        <div>
            <h5>{name} #{data.id}</h5>
            <div>First name: {data.firstName}</div>
            <div>Last name: {data.lastName}</div>
            <div>Gender: {data.gender}</div>
            <div>Age: {data.age}</div>
            <h6>Professions</h6>
            {data.professions.map(el => <div key={el.id}>{el.name}</div>)}
        </div>
    )
}
