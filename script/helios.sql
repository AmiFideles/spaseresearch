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
    type_id      INT    NOT NULL,
    PRIMARY KEY (spaceship_id, type_id),
    FOREIGN KEY (spaceship_id) REFERENCES Spaceships (spaceship_id) ON DELETE CASCADE,
    FOREIGN KEY (type_id) REFERENCES PlanetTypes (type_id) ON DELETE CASCADE
    );


-- Таблица "Исследователь"
CREATE TABLE IF NOT EXISTS Researchers
(
    researcher_id SERIAL PRIMARY KEY,
    first_name    VARCHAR(255)       NOT NULL,
    last_name     VARCHAR(255)       NOT NULL,
    in_expedition BOOLEAN            NOT NULL DEFAULT false,
    age           INT CHECK (age > 18),
    gender        varchar(255)       NOT NULL,
    username      VARCHAR(50) UNIQUE NOT NULL,
    password      VARCHAR(255)       NOT NULL CHECK (LENGTH(password) > 6)
    );

-- Таблица "Экспедиция"
CREATE TABLE IF NOT EXISTS Expeditions
(
    expedition_id          SERIAL PRIMARY KEY,
    spaceship_id           INT NOT NULL,
    commander_id           INT NOT NULL,
    source_station_id      INT NOT NULL,
    destination_station_id INT NOT NULL,                       -- Внешний ключ к таблице "Planets" для указания планеты назначения
    status                 varchar(255) DEFAULT 'IN_PROGRESS', -- Статус экспедиции
    departure_time         TIMESTAMP    DEFAULT NOW(),         -- Время отправления
    end_time               TIMESTAMP    DEFAULT NULL,          -- Время завершения экспедиции
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


CREATE INDEX ON expeditions USING HASH (expedition_id);
CREATE INDEX ON spaceships USING HASH (spaceship_id);
CREATE INDEX ON stations USING HASH (station_id);
CREATE INDEX ON researchers USING HASH (researcher_id);
CREATE INDEX ON planets USING HASH (planet_id);
CREATE INDEX ON galaxies USING HASH (galaxy_id);
CREATE INDEX ON reports USING HASH (report_id);
CREATE INDEX ON cabins USING HASH (cabin_id);
CREATE INDEX ON manufacturers USING HASH (manufacturer_id);
CREATE INDEX ON spaceships USING HASH (current_station_id);
CREATE INDEX ON expeditions USING HASH (source_station_id);
CREATE INDEX ON expeditions USING HASH (destination_station_id);
CREATE INDEX ON expeditions USING HASH(spaceship_id);
CREATE INDEX ON expeditions USING HASH(commander_id);
CREATE INDEX ON ExpeditionResearchers USING HASH(researcher_id);
CREATE INDEX ON ExpeditionResearchers USING HASH(expedition_id);
CREATE INDEX ON expeditions USING BTREE (departure_time);


SELECT * FROM pg_indexes;

-- Функция, которая выбирает корабли, которые свободные корабли
CREATE OR REPLACE FUNCTION get_free_ships()
    RETURNS TABLE
            (
                spaceship_id INT,
                name         varchar(255)
            )
AS
$$
BEGIN
RETURN QUERY
SELECT spaceships.spaceship_id, spaceships.name
FROM spaceships
WHERE in_expedition = FALSE;
END;
$$ LANGUAGE plpgsql;


-- Функция выбрать researcher , которые не заняты

CREATE OR REPLACE FUNCTION get_free_researchers()
    RETURNS TABLE
            (
                researchers_id INT,
                first_name     varchar(255),
                last_name      varchar(255),
                age            integer,
                gender         varchar(255)
            )
AS
$$
BEGIN
RETURN QUERY
SELECT researchers.researcher_id,
       researchers.first_name,
       researchers.last_name,
       researchers.age,
       researchers.gender
FROM researchers
WHERE in_expedition = FALSE;
END;
$$ LANGUAGE plpgsql;


-- Функция для изменения статуса в competed
CREATE OR REPLACE FUNCTION set_expedition_completed(id INT)
    RETURNS VOID AS
$$
BEGIN
UPDATE Expeditions e
SET status = 'COMPLETED'
WHERE e.expedition_id = id;
END;
$$ LANGUAGE plpgsql;



-- -- Функция для создания экспедиции
-- CREATE OR REPLACE FUNCTION create_expedition(p_source_station_id INT, p_destination_station_id INT, p_spaceship_id INT,
--                                              p_commander_id INT)
--     RETURNS VOID AS
-- $$
-- BEGIN
--     -- Вставка новой записи в таблицу "Expeditions"
--     INSERT INTO Expeditions (source_station_id, destination_station_id, spaceship_id, commander_id)
--     VALUES (p_source_station_id, p_destination_station_id, p_spaceship_id, p_commander_id);
-- END;
-- $$ LANGUAGE plpgsql;
--    Функция для создания экспедиции
CREATE OR REPLACE FUNCTION create_expedition(
    p_source_station_id INT,
    p_destination_station_id INT,
    p_spaceship_id INT,
    p_commander_id INT
)
RETURNS INT AS
$$
DECLARE
v_expedition_id INT;
BEGIN
    -- Вставка новой записи в таблицу "Expeditions" и возвращение значения в переменную
INSERT INTO Expeditions (source_station_id, destination_station_id, spaceship_id, commander_id)
VALUES (p_source_station_id, p_destination_station_id, p_spaceship_id, p_commander_id)
    RETURNING expedition_id INTO v_expedition_id;

-- Вернуть идентификатор созданной экспедиции
RETURN v_expedition_id;
END;
$$ LANGUAGE plpgsql;


-- Функция для добавление ресеарчера в экспедицию
CREATE OR REPLACE FUNCTION add_researcher_to_expedition(p_researcher_id INT, p_expedition_id INT)
    RETURNS VOID AS
$$
BEGIN
INSERT INTO ExpeditionResearchers (expedition_id, researcher_id)
VALUES (p_expedition_id, p_researcher_id);
END;
$$ LANGUAGE plpgsql;


CREATE OR REPLACE FUNCTION select_stations_planets_galaxies()
    RETURNS TABLE
            (
                station_name    varchar(255),
                opening_date    date,
                planet_name     varchar(255),
                radius          numeric(10, 2),
                galaxy_name     varchar(255),
                discovery_date  timestamp,
                remote_distance numeric(10, 2)
            )
AS
$$
BEGIN
RETURN QUERY
SELECT *
FROM Stations s
         JOIN Planets p ON s.planet_id = p.planet_id
         JOIN Galaxies g ON p.galaxy_id = g.galaxy_id;
END;
$$ LANGUAGE plpgsql;



CREATE OR REPLACE FUNCTION update_current_station_on_landing()
    RETURNS TRIGGER AS
$$
BEGIN
UPDATE Spaceships
SET current_station_id = NEW.destination_station_id
WHERE spaceship_id = NEW.spaceship_id;
RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Изменяет current_station на нужный
CREATE OR REPLACE TRIGGER update_current_station_trigger
    BEFORE UPDATE
                         ON Expeditions
                         FOR EACH ROW
                         WHEN (OLD.status = 'IN_PROGRESS' AND NEW.status = 'LANDED')
                         EXECUTE FUNCTION update_current_station_on_landing();


CREATE OR REPLACE FUNCTION update_expedition_status()
    RETURNS TRIGGER AS
$$
BEGIN
UPDATE Expeditions
SET status = 'LANDED'
WHERE expedition_id = NEW.expedition_id;
RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Если создался отчет, то статус экспедиции переводится в статус LANDED
CREATE OR REPLACE TRIGGER update_expedition_status_trigger
    AFTER INSERT
    ON Reports
    FOR EACH ROW
EXECUTE FUNCTION update_expedition_status();


CREATE OR REPLACE FUNCTION update_expedition_end_time()
    RETURNS TRIGGER AS
$$
BEGIN
    NEW.end_time := NOW();
UPDATE spaceships
SET in_expedition = FALSE
WHERE spaceship_id = NEW.spaceship_id;
UPDATE researchers
SET in_expedition = FALSE
WHERE researcher_id IN (SELECT e.researcher_id
                        FROM expeditionresearchers e
                        WHERE e.expedition_id = new.expedition_id);
RETURN NEW;
END;
$$ LANGUAGE plpgsql;
-- Если экспедиция завершена, то мы изменяем end_time. Освобождаем корабль и исследователя
CREATE OR REPLACE TRIGGER update_expedition_end_time_trigger
    BEFORE UPDATE
                         ON Expeditions
                         FOR EACH ROW
                         WHEN (NEW.status = 'COMPLETED')
                         EXECUTE FUNCTION update_expedition_end_time();


CREATE OR REPLACE FUNCTION check_expedition_status_transition()
    RETURNS TRIGGER AS
$$
BEGIN
    IF OLD.status = 'LANDED' AND NEW.status = 'COMPLETED' THEN
        RETURN NEW;
    ELSIF OLD.status = 'IN_PROGRESS' AND NEW.status = 'LANDED' THEN
        RETURN NEW;
ELSE
        RAISE EXCEPTION 'Недопустимый переход статуса экспедиции';
END IF;
END;
$$ LANGUAGE plpgsql;

-- Триггер для проверки корректного перехода статуса экспедиции
CREATE OR REPLACE TRIGGER check_expedition_status_transition_trigger
    BEFORE UPDATE
                         ON Expeditions
                         FOR EACH ROW
                         EXECUTE FUNCTION check_expedition_status_transition();


CREATE OR REPLACE FUNCTION update_ship_in_expedition()
    RETURNS TRIGGER AS
$$
BEGIN
UPDATE Spaceships
SET in_expedition = TRUE
WHERE spaceship_id = NEW.spaceship_id;
RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Обновляем значение занятости корабля
CREATE OR REPLACE TRIGGER update_ship_in_expedition_trigger
    AFTER INSERT
    ON Expeditions
    FOR EACH ROW
EXECUTE FUNCTION update_ship_in_expedition();

CREATE OR REPLACE FUNCTION update_researcher_in_expedition()
    RETURNS TRIGGER AS
$$
BEGIN
UPDATE Researchers
SET in_expedition = TRUE
WHERE researcher_id = NEW.researcher_id;
RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Обновляем значение занятости исследователя
CREATE OR REPLACE TRIGGER update_researcher_in_expedition_trigger
    AFTER INSERT
    ON ExpeditionResearchers
    FOR EACH ROW
EXECUTE FUNCTION update_researcher_in_expedition();

CREATE OR REPLACE FUNCTION check_correct_station_spaceship()
    RETURNS TRIGGER AS
$$
BEGIN
    IF NOT EXISTS(SELECT
                  FROM stations
                           JOIN planets p ON stations.planet_id = p.planet_id
                  WHERE station_id = NEW.destination_station_id
                    AND p.type_id IN (SELECT sp.type_id
                                      FROM spaceships s
                                               JOIN spaceshipplanettype sp ON s.spaceship_id = sp.spaceship_id
                                      WHERE s.spaceship_id = NEW.spaceship_id)) THEN
        RAISE EXCEPTION 'Incorrect destination planet type';
END IF;
    IF NOT EXISTS(SELECT *
                  FROM spaceships
                  WHERE spaceships.spaceship_id = NEW.spaceship_id
                    AND spaceships.current_station_id = NEW.source_station_id) THEN
        RAISE EXCEPTION 'Incorrect source station';
END IF;
RETURN NEW;
END;
$$ LANGUAGE plpgsql;


CREATE OR REPLACE TRIGGER check_correct_station_spaceship_trigger
    BEFORE INSERT
    ON Expeditions
    FOR EACH ROW
EXECUTE FUNCTION check_correct_station_spaceship();


-- Заполнение таблицы "Galaxies"
INSERT INTO Galaxies (name, discovery_date, remote_distance)
VALUES
    ('Andromeda', '2000-01-01', 2.537),
    ('Milky Way', '2000-01-02', 3.143);


-- Заполнение таблицы "PlanetTypes"
INSERT INTO PlanetTypes (name)
VALUES
    ('Terrestrial'),
    ('Gas Giant'),
    ('Ice Giant'),
    ('Dwarf Planet'),
    ('Super-Earth'),
    ('Ocean Planet');

-- Заполнение таблицы "Planets"
INSERT INTO Planets (name, galaxy_id, type_id, radius)
VALUES
    ('Xyloth Prime', 1, 1, 8000),
    ('Nebula Nova', 1, 2, 120000),
    ('Celestial Sphere', 1, 3, 55000),
    ('Quasar Quanta', 1, 4, 3000),
    ('Stellaris Sigma', 1, 5, 12000),
    ('Luminar Lambda', 1, 6, 25000);



-- Заполнение таблицы "Stations"
INSERT INTO Stations (name, planet_id, opening_date, latitude, longitude)
VALUES
    ('Alpha Centauri Base', 1, '2022-01-01', 40.7128, -74.0060),
    ('Orion Outpost', 2, '2022-02-01', 34.0522, -118.2437),
    ('Aurora Station', 3, '2022-03-01', 41.8781, -87.6298),
    ('Nebula Nexus', 4, '2022-04-01', 29.7604, -95.3698),
    ('Pulsar Port', 5, '2022-05-01', 37.7749, -122.4194),
    ('Quasar Hub', 6, '2022-06-01', 32.7767, -96.7970),
    ('Vortex Vault', 6, '2022-07-01', 39.9526, -75.1652),
    ('Celestial Citadel', 1, '2022-08-01', 33.4484, -112.0740);

-- Заполнение таблицы "Manufacturers"
INSERT INTO Manufacturers (name, country)
VALUES
    ('SpaceX', 'USA'),
    ('Boeing', 'USA'),
    ('Roscosmos', 'Russia'),
    ('ESA', 'Europe');

-- Заполнение таблицы "Spaceships" с current_station_id от 1 до 8
INSERT INTO Spaceships (name, in_expedition, current_station_id, date_of_construction, manufacturer_id, max_speed, capacity)
VALUES
    -- SpaceX
    ('Falcon-1', true, 1, '2005-05-10', 1, 30000, 5),
    ('Falcon-9', true, 2, '2008-07-25', 1, 32000, 7),
    ('Falcon-Heavy', true, 3, '2010-02-06', 1, 45000, 10),
    ('Starship-X', true, 4, '2012-03-15', 1, 50000, 12),
    ('Red Dragon', true, 5, '2014-08-20', 1, 28000, 8),
    ('Mars Express', true, 6, '2009-11-11', 1, 32000, 6),
    ('Galactic Voyager', true, 7, '2008-06-25', 1, 40000, 7),
    ('Dreamliner-1', true, 8, '2011-07-01', 2, 25000, 6),

    ('Dreamliner-2', false, 1, '2006-12-05', 2, 28000, 8),
    ('SpaceCruiser', true, 2, '2010-02-28', 2, 30000, 5),
    ('AeroExplorer', false, 3, '2007-04-18', 2, 35000, 9),
    ('StarHawk', true, 4, '2007-03-08', 2, 42000, 7),
    ('Celestial Chariot', false, 5, '2014-05-20', 2, 38000, 8),
    ('Cosmic Cruiser', true, 6, '2004-11-30', 3, 28000, 7),
    ('Lunar Lander', false, 7, '2008-10-15', 3, 32000, 5),
    ('Galaxy Guardian', true, 8, '2007-03-08', 3, 30000, 6),

    ('Astronavigator', false, 1, '2009-08-10', 3, 35000, 8),
    ('Stellar Sprinter', true, 2, '2006-02-12', 3, 25000, 7),
    ('Interstellar Explorer', false, 3, '2005-01-22', 3, 33000, 8),
    ('EuroExplorer-1', true, 4, '2013-09-01', 4, 28000, 8),
    ('EuroExplorer-2', false, 5, '2005-08-22', 4, 32000, 6),
    ('SolarSailor', true, 6, '2010-04-04', 4, 30000, 7),
    ('Asteroid Assailant', false, 7, '2008-06-14', 4, 34000, 9),
    ('Cosmic Cruiser II', true, 8, '2012-11-30', 4, 36000, 7),

    ('Galactic Guardian II', true, 1, '2013-05-15', 1, 40000, 8),
    ('Starlight Express', false, 2, '2014-02-28', 1, 35000, 6),
    ('Red Nebula', true, 3, '2015-07-10', 1, 42000, 7),
    ('Solar Skipper', false, 4, '2012-09-18', 1, 38000, 8),
    -- Boeing
    ('StarSeeker', true, 5, '2014-11-01', 2, 33000, 7),
    ('AeroSpectre', false, 6, '2013-04-15', 2, 31000, 8),
    ('Celestial Cruiser', true, 7, '2015-01-22', 2, 36000, 9),
    ('Lunar Voyager', false, 8, '2014-06-10', 2, 29000, 6);


INSERT INTO Researchers (first_name, last_name, in_expedition, age, gender, username, password)
VALUES
    ('Emma', 'Taylor', false, 31, 'FEMALE', 'emmataylorq', 'passwordxyz'),
    ('Aiden', 'Anderson', true, 34, 'MALE', 'aidenandersonw', 'password123'),
    ('Grace', 'Harris', false, 28, 'FEMALE', 'graceharrise', 'password456'),
    ('Liam', 'Smith', true, 30, 'MALE', 'liamsmithr', 'password789'),
    ('Zoe', 'Johnson', false, 36, 'FEMALE', 'zoejohnsont', 'passwordabc'),
    ('Caleb', 'Martin', true, 29, 'MALE', 'calebmartine', 'passwordxyz'),
    ('Ella', 'Thompson', false, 33, 'FEMALE', 'ellathompsone', 'password789'),
    ('Owen', 'Baker', true, 35, 'MALE', 'owenbakers', 'passwordabc'),
    ('Ava', 'White', false, 32, 'FEMALE', 'avawhites', 'password123'),
    ('Noah', 'Moore', true, 30, 'MALE', 'noahmoores', 'password456'),
    ('Mia', 'Davis', false, 27, 'FEMALE', 'miadavisec', 'passwordxyz'),
    ('Ethan', 'Brown', true, 28, 'MALE', 'ethanbrown', 'password123'),
    ('Lily', 'Garcia', false, 32, 'FEMALE', 'lilygarcias', 'password456'),
    ('Logan', 'Robinson', true, 29, 'MALE', 'loganrobinsonz', 'password789'),
    ('Chloe', 'Hall', false, 30, 'FEMALE', 'chloehalls', 'passwordabc'),
    ('Jackson', 'Miller', true, 34, 'MALE', 'jacksonmillers', 'passwordxyz'),
    ('Sophie', 'Anderson', false, 28, 'FEMALE', 'sophieandersons', 'password123'),
    ('Daniel', 'Carter', true, 33, 'MALE', 'danielcarterz', 'password456'),
    ('Avery', 'King', false, 29, 'FEMALE', 'averykingc', 'password789'),
    ('Lucas', 'Adams', true, 31, 'MALE', 'lucasadamsa', 'passwordabc'),
    ('Emma', 'Taylor', false, 30, 'FEMALE', 'emmatayloress', 'passwordxyz'),
    ('Aiden', 'Anderson', true, 36, 'MALE', 'aidenandersond', 'password123'),
    ('Grace', 'Harris', false, 32, 'FEMALE', 'graceharrisr', 'password456'),
    ('Liam', 'Smith', true, 33, 'MALE', 'liamsmitht', 'password789'),
    ('Zoe', 'Johnson', false, 31, 'FEMALE', 'zoejohnsony', 'passwordabc'),
    ('Caleb', 'Martin', true, 29, 'MALE', 'calebmartinu', 'passwordxyz'),
    ('Ella', 'Thompson', false, 34, 'FEMALE', 'ellathompsonu', 'password789'),
    ('Owen', 'Baker', true, 30, 'MALE', 'owenbakeru', 'passwordabc'),
    ('Ava', 'White', false, 29, 'FEMALE', 'avawhitje', 'password123'),
    ('Noah', 'Moore', true, 31, 'MALE', 'noahmooreg', 'password456'),
    ('Mia', 'Davis', false, 28, 'FEMALE', 'miadavins', 'passwordxyz'),
    ('Ethan', 'Brown', true, 32, 'MALE', 'ethanbrowmn', 'password123'),
    ('Lily', 'Garcia', false, 29, 'FEMALE', 'lilygarcgia', 'password456'),
    ('Logan', 'Robinson', true, 30, 'MALE', 'loganrobinmson', 'password789'),
    ('Chloe', 'Hall', false, 33, 'FEMALE', 'chloehalml', 'passwordabc'),
    ('Jackson', 'Miller', true, 35, 'MALE', 'jacksonmmiller', 'passwordxyz'),
    ('Sophie', 'Anderson', false, 29, 'FEMALE', 'sophieandgerson', 'password123'),
    ('Daniel', 'Carter', true, 34, 'MALE', 'danielfcarter', 'password456'),
    ('Avery', 'King', false, 30, 'FEMALE', 'averykikng', 'password789'),
    ('Lucas', 'Adams', true, 32, 'MALE', 'lucasadamgs', 'passwordabc');


-- Заполнение таблицы "Professions"
INSERT INTO Professions ( name)
VALUES
    ('Astronomer'),
    ('Biologist'),
    ('Engineer'),
    ('Geologist'),
    ('Captain');

INSERT INTO ResearcherProfessions(researcher_id, profession_id)
VALUES
    (2, 5),
    (4, 5),
    (6, 5),
    (8, 5),
    (10,5),
    (12,5),
    (14,5),
    (16,5),
    (18,5),
    (20,5),
    (22,5),
    (24,5),
    (26,5);

INSERT INTO ResearcherProfessions (researcher_id, profession_id)
SELECT researcher_id, profession_id
FROM (SELECT researcher_id, profession_id FROM Researchers CROSS JOIN Professions) AS cross_join
WHERE researcher_id NOT IN (2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26)
ORDER BY random()
    LIMIT 80;



-- Заполнение таблицы "Cabins"
INSERT INTO Cabins (name, limited_access, volume_cubic_meters, spaceship_id)
SELECT
    CONCAT('Cabin ', t.number) AS name,
    FALSE AS limited_access,
    50 AS volume_cubic_meters,
    t.spaceship_id
FROM (
         SELECT
             ROW_NUMBER() OVER (ORDER BY spaceship_id) AS number,
                 spaceship_id
         FROM Spaceships
         ORDER BY random()
             LIMIT 62
     ) AS t;

UPDATE Cabins
SET limited_access = true
WHERE cabin_id <= 5;

INSERT INTO ProfessionsCabinAssignment (profession_id, cabin_id)
VALUES
    (1, 1),
    (2, 2),
    (4, 4),
    (5, 5),
    (1, 2),
    (4, 2),
    (5, 2),
    (2, 3),
    (4, 3),
    (5, 3),
    (1, 4),
    (2, 4),
    (5, 4),
    (2, 5),
    (4, 5);

-- Заполнение таблицы "SpaceshipPlanetType" случайными значениями many-to-many
INSERT INTO SpaceshipPlanetType (spaceship_id, type_id)
SELECT
    s.spaceship_id,
    pt.type_id
FROM
    (SELECT spaceship_id FROM Spaceships ORDER BY random() LIMIT (SELECT COUNT(*) FROM Spaceships)) AS s,
    (SELECT type_id FROM planettypes ORDER BY random() LIMIT (SELECT COUNT(*) FROM planettypes)) AS pt;

-- Создание 16 экспедиций
INSERT INTO Expeditions (spaceship_id, commander_id, source_station_id, destination_station_id, status, departure_time, end_time)
VALUES
    (1,  2, 1, 1, 'IN_PROGRESS', '2022-03-01 12:00:00', NULL),
    (2,  4, 2, 3, 'IN_PROGRESS', '2022-04-01 14:00:00', NULL),
    (3,  6, 3, 5, 'IN_PROGRESS', '2022-05-01 16:00:00', NULL),
    (4,  8, 4, 7, 'IN_PROGRESS', '2022-06-01 18:00:00', NULL),
    (5,  10, 5, 1, 'IN_PROGRESS', '2022-07-01 20:00:00', NULL),
    (6,  12, 6, 3, 'IN_PROGRESS', '2022-08-01 22:00:00', NULL),
    (7,  14, 7, 5, 'IN_PROGRESS', '2022-09-01 00:00:00', NULL),
    (8,  16, 8, 7, 'IN_PROGRESS', '2022-10-01 02:00:00', NULL),
    (18, 18, 2, 1, 'IN_PROGRESS', '2022-11-01 04:00:00', NULL),
    (20, 20, 4, 3, 'IN_PROGRESS', '2022-12-01 06:00:00', NULL),
    (22, 22, 6, 5, 'IN_PROGRESS', '2023-01-01 08:00:00', NULL),
    (24, 24, 8, 7, 'IN_PROGRESS', '2023-02-01 10:00:00', NULL),
    (26, 26, 2, 1, 'IN_PROGRESS', '2023-03-01 12:00:00', NULL);


INSERT INTO ExpeditionResearchers (expedition_id, researcher_id)
VALUES
    (1, 31),
    (1, 2),
    (2, 29),
    (2, 4),
    (3, 27),
    (3, 6),
    (4, 25),
    (4, 8),
    (5, 23),
    (5, 10),
    (6, 21),
    (6, 12),
    (7, 19),
    (7, 14),
    (8, 17),
    (8, 16),
    (1, 15),
    (1, 13),
    (2, 11),
    (2, 9),
    (9, 18),
    (10, 20),
    (11, 22),
    (12, 24),
    (13, 26);

-- Добавление 5 значений в таблицу "Reports" с более содержательными описаниями
INSERT INTO Reports (expedition_id, description)
VALUES
    (9, 'The expedition was a thrilling journey through uncharted space. Exciting discoveries were made, and the crew performed admirably.'),
    (10, 'Despite a few challenges, Expedition 10 successfully navigated through asteroid fields and gathered valuable data about distant planets.'),
    (11, 'Expedition faced unexpected meteor showers, but the crew'),
    (12, 'Report for Expedition  is a comprehensive overview of the scientific experiments conducted on board, revealing new insights into space biology.'),
    (13, 'During Expedition 13, the crew encountered a mysterious cosmic phenomenon, providing a unique opportunity for groundbreaking research.');

INSERT INTO BreakdownTypes (name)
VALUES
    ('Engine Failure'),
    ('Life Support Failure'),
    ('Navigation Failure'),
    ('Communication Failure'),
    ('Temperature problem');
-- Заполнение таблицы "ReportsBreakdowns"
INSERT INTO ReportsBreakdowns (report_id, breakdown_type_id)
VALUES
    (1, 1),
    (2, 2),
    (3, 3),
    (4, 4),
    (5, 5);


select set_expedition_completed(12);
select set_expedition_completed(13);
