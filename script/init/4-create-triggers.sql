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
