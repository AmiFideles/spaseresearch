-- Таблица "Галактика"
CREATE TABLE IF NOT EXISTS Galaxies
(
    galaxy_id       SERIAL PRIMARY KEY,
    name            VARCHAR(255)   NOT NULL,
    discovery_date  TIMESTAMP      NOT NULL,
    remote_distance DECIMAL(10, 2) NOT NULL -- Отдаленность от Земли в световых годах
);

-- Таблица "Тип планет"
CREATE TABLE IF NOT EXISTS PlanetTypes
(
    type_id SERIAL PRIMARY KEY,
    name    VARCHAR(255) NOT NULL
);

-- Обновленная таблица "Планета"
CREATE TABLE IF NOT EXISTS Planets
(
    planet_id SERIAL PRIMARY KEY,
    name      VARCHAR(255)   NOT NULL,
    galaxy_id INT            NOT NULL,
    type_id   INT            NOT NULL,
    radius    DECIMAL(10, 2) NOT NULL, -- Радиус планеты
    FOREIGN KEY (galaxy_id) REFERENCES Galaxies (galaxy_id) ON DELETE CASCADE,
    FOREIGN KEY (type_id) REFERENCES PlanetTypes (type_id) ON DELETE CASCADE
);

-- Таблица "Станция"
CREATE TABLE IF NOT EXISTS Stations
(
    station_id   SERIAL PRIMARY KEY,
    name         VARCHAR(255)  NOT NULL,
    planet_id    INT           NOT NULL,
    opening_date DATE          NOT NULL, -- Год основания станции
    latitude     DECIMAL(9, 6) NOT NULL, -- Широта
    longitude    DECIMAL(9, 6) NOT NULL, -- Долгота
    FOREIGN KEY (planet_id) REFERENCES Planets (planet_id) ON DELETE CASCADE
);


-- Таблица "Manufacturer" для хранения информации о производителях
CREATE TABLE IF NOT EXISTS Manufacturers
(
    manufacturer_id SERIAL PRIMARY KEY,
    name            VARCHAR(255) NOT NULL,
    country         VARCHAR(100) NOT NULL -- Страна производства, например
);

-- Таблица "Космолет"
CREATE TABLE IF NOT EXISTS Spaceships
(
    spaceship_id         SERIAL PRIMARY KEY,
    name                 VARCHAR(255)   NOT NULL,
    in_expedition        BOOLEAN DEFAULT FALSE,
    current_station_id   INT            NOT NULL,
    date_of_construction DATE           NOT NULL,                       -- Год постройки
    manufacturer_id      INT            NOT NULL,                       -- Внешний ключ к таблице "Manufacturer"
    max_speed            DECIMAL(10, 2) NOT NULL CHECK (max_speed > 0), -- Максимальная скорость (в км/с, например)
    capacity             INT            NOT NULL CHECK (capacity > 0),  -- Вместимость
    FOREIGN KEY (manufacturer_id) REFERENCES Manufacturers (manufacturer_id),
    FOREIGN KEY (current_station_id) REFERENCES Stations (station_id)
);

-- Таблица "SpaceshipPlanetType" для связи многие ко многим между "кораблями" и "типами планет"
CREATE TABLE IF NOT EXISTS SpaceshipPlanetType
(
    spaceship_id SERIAL NOT NULL,
    type_id      INT NOT NULL,
    PRIMARY KEY (spaceship_id, type_id),
    FOREIGN KEY (spaceship_id) REFERENCES Spaceships (spaceship_id) ON DELETE CASCADE,
    FOREIGN KEY (type_id) REFERENCES PlanetTypes (type_id) ON DELETE CASCADE
);


-- Enum для пола
-- CREATE TYPE Gender AS ENUM ('Male', 'Female');


-- Таблица "Исследователь"
CREATE TABLE IF NOT EXISTS Researchers
(
    researcher_id SERIAL PRIMARY KEY,
    first_name    VARCHAR(255)       NOT NULL,
    last_name     VARCHAR(255)       NOT NULL,
    in_expedition BOOLEAN            NOT NULL,
    age           INT CHECK (age > 18),
    gender        Gender             NOT NULL,
    username      VARCHAR(50) UNIQUE NOT NULL,
    password      VARCHAR(255)       NOT NULL CHECK (LENGTH(password) > 6)
);

-- CREATE TYPE ExpeditionStatus AS ENUM ('IN_PROGRESS', 'LANDED', 'COMPLETED');
-- Таблица "Экспедиция"
CREATE TABLE IF NOT EXISTS Expeditions
(
    expedition_id          SERIAL PRIMARY KEY,
    spaceship_id           INT NOT NULL,
    commander_id           INT NOT NULL,
    source_station_id      INT NOT NULL,
    destination_station_id INT NOT NULL,                           -- Внешний ключ к таблице "Planets" для указания планеты назначения
    status                 ExpeditionStatus DEFAULT 'IN_PROGRESS', -- Статус экспедиции
    departure_time         TIMESTAMP        DEFAULT NOW(),         -- Время отправления
    end_time               TIMESTAMP        DEFAULT NULL,          -- Время завершения экспедиции
    FOREIGN KEY (spaceship_id) REFERENCES Spaceships (spaceship_id),
    FOREIGN KEY (commander_id) REFERENCES Researchers (researcher_id),
    FOREIGN KEY (destination_station_id) REFERENCES Stations (station_id),
    FOREIGN KEY (source_station_id) REFERENCES Stations (station_id)
);

-- Таблица "Отчет"
CREATE TABLE IF NOT EXISTS Reports
(
    report_id     SERIAL PRIMARY KEY,
    expedition_id INT UNIQUE NOT NULL,
    description   TEXT,
    FOREIGN KEY (expedition_id) REFERENCES Expeditions (expedition_id)
);

-- Таблица "Тип поломки"
CREATE TABLE IF NOT EXISTS BreakdownTypes
(
    breakdown_type_id SERIAL PRIMARY KEY,
    name              VARCHAR(255) NOT NULL
);

-- Таблица связи "Поломка и Отчет" MtM
CREATE TABLE IF NOT EXISTS ReportsBreakdowns
(
    report_id         INT,
    breakdown_type_id INT,
    PRIMARY KEY (report_id, breakdown_type_id),
    FOREIGN KEY (report_id) REFERENCES Reports (report_id) ON DELETE CASCADE,
    FOREIGN KEY (breakdown_type_id) REFERENCES BreakdownTypes (breakdown_type_id)
);


-- Таблица "ЭкспедицияИсследователь" для связи многие ко многим между "экспедициями" и "исследователями"
CREATE TABLE IF NOT EXISTS ExpeditionResearchers
(
    expedition_id INT,
    researcher_id INT,
    PRIMARY KEY (expedition_id, researcher_id),
    FOREIGN KEY (expedition_id) REFERENCES Expeditions (expedition_id) ON DELETE CASCADE,
    FOREIGN KEY (researcher_id) REFERENCES Researchers (researcher_id)
);

-- Таблица "Профессия"
CREATE TABLE IF NOT EXISTS Professions
(
    profession_id SERIAL PRIMARY KEY,
    name          VARCHAR(255) NOT NULL
);


-- Таблица "ИсследовательПрофессия" для связи многие ко многим между "исследователями" и "профессиями"
CREATE TABLE IF NOT EXISTS ResearcherProfessions
(
    researcher_id INT,
    profession_id INT,
    PRIMARY KEY (researcher_id, profession_id),
    FOREIGN KEY (researcher_id) REFERENCES Researchers (researcher_id) ON DELETE CASCADE,
    FOREIGN KEY (profession_id) REFERENCES Professions (profession_id) ON DELETE CASCADE
);


-- Таблица "Каюта"
CREATE TABLE IF NOT EXISTS Cabins
(
    cabin_id            SERIAL PRIMARY KEY,
    name                TEXT                  NOT NULL,
    limited_access      BOOLEAN DEFAULT FALSE NOT NULL,
    volume_cubic_meters INT                   NOT NULL CHECK ( volume_cubic_meters > 0 ),
    spaceship_id        INT, -- Внешний ключ к таблице "Spaceships" для указания космолета
    FOREIGN KEY (spaceship_id) REFERENCES Spaceships (spaceship_id) ON DELETE CASCADE
);

-- Таблица "ИсследовательКаюта" для связи многие ко многим между "исследователями" и "каютами"
CREATE TABLE IF NOT EXISTS ProfessionsCabinAssignment
(
    profession_id INT,
    cabin_id      INT,
    PRIMARY KEY (profession_id, cabin_id),
    FOREIGN KEY (profession_id) REFERENCES Professions (profession_id) ON DELETE CASCADE,
    FOREIGN KEY (cabin_id) REFERENCES Cabins (cabin_id) ON DELETE CASCADE
);

