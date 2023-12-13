-- Заполнение таблицы "Galaxies"
INSERT INTO Galaxies (galaxy_id, name, discovery_date, remote_distance)
VALUES
    (1, 'Andromeda', '2000-01-01', 2.537),
    (2, 'Milky Way', '2000-01-02', 0),
    (3, 'Triangulum', '2000-01-03', 3.073),
    (4, 'Whirlpool', '2000-01-04', 0.31);

-- Заполнение таблицы "PlanetTypes"
INSERT INTO PlanetTypes (type_id, name)
VALUES
    (1, 'Terrestrial'),
    (2, 'Gas Giant'),
    (3, 'Ice Giant'),
    (4, 'Dwarf Planet');

-- Заполнение таблицы "Planets"
INSERT INTO Planets (planet_id, name, galaxy_id, type_id, radius)
VALUES
    (1, 'Earth', 2, 1, 6371),
    (2, 'Jupiter', 2, 2, 69911),
    (3, 'Neptune', 2, 3, 24622),
    (4, 'Pluto', 2, 4, 1188);

-- Заполнение таблицы "Stations"
INSERT INTO Stations (station_id, name, planet_id, opening_date, latitude, longitude)
VALUES
    (1, 'International Space Station', 1, '1998-11-20', 51.6, -0.1),
    (2, 'Lunar Gateway', 2, '2024-01-01', 0, 0),
    (3, 'Mars Base Alpha', 3, '2030-05-15', 0, 0),
    (4, 'Europa Outpost', 4, '2040-09-10', 0, 0);

-- Заполнение таблицы "Manufacturers"
INSERT INTO Manufacturers (manufacturer_id, name, country)
VALUES
    (1, 'SpaceX', 'USA'),
    (2, 'Boeing', 'USA'),
    (3, 'Roscosmos', 'Russia'),
    (4, 'ESA', 'Europe');

-- Заполнение таблицы "Spaceships"
INSERT INTO Spaceships (spaceship_id, name, in_expedition, current_station_id, date_of_construction, manufacturer_id, max_speed, capacity)
VALUES
    (1, 'Dragon', true, 1, '2010-12-08', 1, 28000, 7),
    (2, 'Starliner', true, 1, '2021-01-01', 2, 28500, 7),
    (3, 'Soyuz', true, 1, '1967-11-28', 3, 28000, 3),
    (4, 'Ariane 5', true, 1, '1996-06-04', 4, 10800, 10);

-- Заполнение таблицы "Researchers"
INSERT INTO Researchers (first_name, last_name, in_expedition, age, gender, username, password)
VALUES
    ('John', 'Doe', true, 35, 'Male', 'johndoe', 'password123'),
    ('Jane', 'Smith', false, 28, 'Female', 'janesmith', 'password456'),
    ('Michael', 'Johnson', true, 42, 'Male', 'michaeljohnson', 'password789'),
    ('Emily', 'Davis', false, 31, 'Female', 'emilydavis', 'passwordabc');

-- Заполнение таблицы "Professions"
INSERT INTO Professions (profession_id, name)
VALUES
    (1, 'Astronomer'),
    (2, 'Biologist'),
    (3, 'Engineer'),
    (4, 'Geologist');

-- Заполнение таблицы "ResearcherProfessions"
INSERT INTO ResearcherProfessions (researcher_id, profession_id)
VALUES
    (1, 1),
    (2, 2),
    (3, 3),
    (4, 4);

-- Заполнение таблицы "Cabins"
INSERT INTO Cabins (cabin_id, name, limited_access, volume_cubic_meters, spaceship_id)
VALUES
    (1, 'Cabin 1', false, 50, 1),
    (2, 'Cabin 2', false, 50, 2),
    (3, 'Cabin 3', false, 50, 3),
    (4, 'Cabin 4', false, 50, 4);

-- Заполнение таблицы "ProfessionsCabinAssignment"
INSERT INTO ProfessionsCabinAssignment (profession_id, cabin_id)
VALUES
    (1, 1),
    (2, 2),
    (3, 3),
    (4, 4);

-- Заполнение таблицы "SpaceshipPlanetType"
INSERT INTO SpaceshipPlanetType (spaceship_id, type_id)
VALUES
    (1, 4),
    (2, 2),
    (3, 3),
    (4, 4);

-- Заполнение таблицы "Expeditions"
INSERT INTO Expeditions (expedition_id, spaceship_id, commander_id, source_station_id, destination_station_id, status, departure_time, end_time)
VALUES
    (1, 1, 1, 1, 4, 'IN_PROGRESS', '2022-01-01 08:00:00', NULL),
    (2, 2, 2, 1, 2, 'IN_PROGRESS', '2022-02-01 10:00:00', NULL),
    (3, 3, 3, 1, 3, 'IN_PROGRESS', '2022-03-01 12:00:00', NULL),
    (4, 4, 4, 1, 4, 'IN_PROGRESS', '2022-04-01 14:00:00', NULL);


-- Заполнение таблицы "ExpeditionResearchers"
INSERT INTO ExpeditionResearchers (expedition_id, researcher_id)
VALUES
    (1, 1),
    (2, 2),
    (3, 3),
    (4, 4);

-- Заполнение таблицы "Reports"
INSERT INTO Reports (report_id, expedition_id, description)
VALUES
    (1, 1, 'Initial report for expedition 1'),
    (2, 2, 'Initial report for expedition 2'),
    (3, 3, 'Initial report for expedition 3'),
    (4, 4, 'Initial report for expedition 4');

-- Заполнение таблицы "BreakdownTypes"
INSERT INTO BreakdownTypes (breakdown_type_id, name)
VALUES
    (1, 'Engine Failure'),
    (2, 'Life Support Failure'),
    (3, 'Navigation Failure'),
    (4, 'Communication Failure');

-- Заполнение таблицы "ReportsBreakdowns"
INSERT INTO ReportsBreakdowns (report_id, breakdown_type_id)
VALUES
    (1, 1),
    (2, 2),
    (3, 3),
    (4, 4);
