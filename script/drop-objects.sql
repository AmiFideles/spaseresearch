DROP TRIGGER IF EXISTS update_current_station_trigger ON Expeditions;
DROP TRIGGER IF EXISTS update_expedition_status_trigger ON Reports;
DROP TRIGGER IF EXISTS update_expedition_end_time_trigger ON Expeditions;
DROP TRIGGER IF EXISTS check_expedition_status_transition_trigger ON Expeditions;
DROP TRIGGER IF EXISTS update_ship_in_expedition_trigger ON Expeditions;
DROP TRIGGER IF EXISTS update_researcher_in_expedition_trigger ON ExpeditionResearchers;
DROP TRIGGER IF EXISTS check_correct_station_spaceship_trigger ON Expeditions;


DROP FUNCTION IF EXISTS update_current_station;
DROP FUNCTION IF EXISTS update_current_station_on_landing;
DROP FUNCTION IF EXISTS update_expedition_status;
DROP FUNCTION IF EXISTS update_expedition_end_time;
DROP FUNCTION IF EXISTS check_expedition_status_transition;
DROP FUNCTION IF EXISTS update_ship_in_expedition;
DROP FUNCTION IF EXISTS update_researcher_in_expedition;
DROP FUNCTION IF EXISTS check_correct_station_spaceship;

DROP FUNCTION IF EXISTS get_free_ships;
DROP FUNCTION IF EXISTS get_free_researchers;
DROP FUNCTION IF EXISTS set_expedition_completed;
DROP FUNCTION IF EXISTS create_expedition;
DROP FUNCTION IF EXISTS add_researcher_to_expedition;
DROP FUNCTION IF EXISTS select_stations_planets_galaxies;

DROP INDEX IF EXISTS expeditions_expedition_id_idx;;
DROP INDEX IF EXISTS spaceships_spaceship_id_idx;
DROP INDEX IF EXISTS stations_station_id_idx;
DROP INDEX IF EXISTS researchers_researcher_id_idx;;
DROP INDEX IF EXISTS planets_planet_id_idx;
DROP INDEX IF EXISTS galaxies_galaxy_id_idx;
DROP INDEX IF EXISTS reports_report_id_idx;
DROP INDEX IF EXISTS cabins_cabin_id_idx;
DROP INDEX IF EXISTS manufacturers_manufacturer_id_idx;
DROP INDEX IF EXISTS spaceships_current_station_id_idx;
DROP INDEX IF EXISTS expeditions_source_station_id_idx;
DROP INDEX IF EXISTS expeditions_destination_station_id_idx;
DROP INDEX IF EXISTS expeditions_departure_time_idx;

-- Удаление связи "ИсследовательКаюта"
DROP TABLE IF EXISTS ProfessionsCabinAssignment;

-- Удаление связи "Каюта" и "Космолет"
DROP TABLE IF EXISTS Cabins;

-- Удаление связи "ИсследовательПрофессия"
DROP TABLE IF EXISTS ResearcherProfessions;

-- Удаление внешних ключей в таблице "Профессия"
DROP TABLE IF EXISTS Professions;

-- Удаление связи "ЭкспедицияИсследователь"
DROP TABLE IF EXISTS ExpeditionResearchers;


-- Удаление связи "Поломка и Отчет"
DROP TABLE IF EXISTS ReportsBreakdowns;


-- Удаление внешних ключей в таблице "Отчет"
-- Удаление внешнего ключа "Экспедиция" в "Отчет"
DROP TABLE IF EXISTS Reports;

-- Удаление внешних ключей в таблице "ЭкспедицияИсследователь"
-- Удаление внешнего ключа "Экспедиция" в "ЭкспедицияИсследователь"
DROP TABLE IF EXISTS ExpeditionResearchers;

-- Удаление внешних ключей в таблице "Экспедиция"
-- Удаление внешнего ключа "Космолет" в "Экспедиция"
DROP TABLE IF EXISTS Expeditions;

-- Удаление внешних ключей в таблице "Профессия"
DROP TABLE IF EXISTS Professions;

DROP TABLE IF EXISTS spaceshipplanettype;
-- Удаление внешних ключей в таблице "Космолет"
-- Удаление внешнего ключа "Производитель" в "Космолет"
DROP TABLE IF EXISTS Spaceships;

-- Удаление внешних ключей в таблице "Станция"
-- Удаление внешнего ключа "Планета" в "Станция"
DROP TABLE IF EXISTS Stations;

DROP TABLE IF EXISTS Planets;

-- Удаление внешних ключей в таблице "Галактика"
DROP TABLE IF EXISTS Galaxies;
-- Удаление внешних ключей в таблице "Производитель"
-- Удаление внешнего ключа "Космолет" в "Производитель"
DROP TABLE IF EXISTS Manufacturers;

-- Удаление внешних ключей в таблице "Тип планеты"
DROP TABLE IF EXISTS PlanetTypes;

DROP TABLE IF EXISTS Breakdowntypes;

DROP TABLE IF EXISTS Researchers;
