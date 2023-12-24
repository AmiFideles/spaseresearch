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
   -- Функция для создания экспедиции
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



