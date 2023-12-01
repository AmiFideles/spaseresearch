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