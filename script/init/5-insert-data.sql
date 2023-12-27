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

INSERT INTO ResearcherProfessions (researcher_id, profession_id)
SELECT researcher_id, profession_id
FROM (SELECT researcher_id, profession_id FROM Researchers CROSS JOIN Professions) AS cross_join
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
    (1, 2, 1, 1, 'IN_PROGRESS', '2022-03-01 12:00:00', NULL),
    (2, 4, 2, 3, 'IN_PROGRESS', '2022-04-01 14:00:00', NULL),
    (3, 6, 3, 5, 'IN_PROGRESS', '2022-05-01 16:00:00', NULL),
    (4, 8, 4, 7, 'IN_PROGRESS', '2022-06-01 18:00:00', NULL),
    (5, 10, 5, 1, 'IN_PROGRESS', '2022-07-01 20:00:00', NULL),
    (6, 12, 6, 3, 'IN_PROGRESS', '2022-08-01 22:00:00', NULL),
    (7, 14, 7, 5, 'IN_PROGRESS', '2022-09-01 00:00:00', NULL),
    (8, 16, 8, 7, 'IN_PROGRESS', '2022-10-01 02:00:00', NULL),
    (18, 18, 2, 1, 'IN_PROGRESS', '2022-11-01 04:00:00', NULL),
    (20, 20, 4, 3, 'IN_PROGRESS', '2022-12-01 06:00:00', NULL),
    (22, 22, 6, 5, 'IN_PROGRESS', '2023-01-01 08:00:00', NULL),
    (24, 24, 8, 7, 'IN_PROGRESS', '2023-02-01 10:00:00', NULL),
    (26, 26, 2, 1, 'IN_PROGRESS', '2023-03-01 12:00:00', NULL);


INSERT INTO ExpeditionResearchers (expedition_id, researcher_id)
VALUES
    (1, 31),
    (2, 29),
    (3, 27),
    (4, 25),
    (5, 23),
    (6, 21),
    (7, 19),
    (8, 17),
    (1, 15),
    (1, 13),
    (2, 11),
    (2, 9),
    (9, 1),
    (10, 2),
    (11, 3),
    (12, 4),
    (13, 5),
    (9, 6),
    (10, 7),
    (11, 8),
    (12, 9),
    (13, 10);

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