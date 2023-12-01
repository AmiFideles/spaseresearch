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