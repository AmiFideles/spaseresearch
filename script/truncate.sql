-- Удаление связи "ИсследовательКаюта"
TRUNCATE TABLE ProfessionsCabinAssignment CASCADE;
-- Удаление связи "Каюта" и "Космолет"
TRUNCATE TABLE Cabins CASCADE;
-- Удаление связи "ИсследовательПрофессия"
TRUNCATE TABLE ResearcherProfessions CASCADE;
-- Удаление внешних ключей в таблице "Профессия"
TRUNCATE TABLE Professions CASCADE;
-- Удаление связи "ЭкспедицияИсследователь"
TRUNCATE TABLE ExpeditionResearchers CASCADE;
-- Удаление связи "Поломка и Отчет"
TRUNCATE TABLE ReportsBreakdowns CASCADE;
-- Удаление внешних ключей в таблице "Отчет"
-- Удаление внешнего ключа "Экспедиция" в "Отчет"
TRUNCATE TABLE Reports CASCADE;
-- Удаление внешних ключей в таблице "ЭкспедицияИсследователь"
-- Удаление внешнего ключа "Экспедиция" в "ЭкспедицияИсследователь"
TRUNCATE TABLE ExpeditionResearchers CASCADE;
-- Удаление внешних ключей в таблице "Экспедиция"
-- Удаление внешнего ключа "Космолет" в "Экспедиция"
TRUNCATE TABLE Expeditions CASCADE;
-- Удаление внешних ключей в таблице "Профессия"
TRUNCATE TABLE Professions CASCADE;
TRUNCATE TABLE spaceshipplanettype CASCADE;
-- Удаление внешних ключей в таблице "Космолет"
-- Удаление внешнего ключа "Производитель" в "Космолет"
TRUNCATE TABLE Spaceships CASCADE;
-- Удаление внешних ключей в таблице "Станция"
-- Удаление внешнего ключа "Планета" в "Станция"
TRUNCATE TABLE Stations CASCADE;
TRUNCATE TABLE Planets CASCADE;
-- Удаление внешних ключей в таблице "Галактика"
TRUNCATE TABLE Galaxies CASCADE;
-- Удаление внешних ключей в таблице "Производитель"
-- Удаление внешнего ключа "Космолет" в "Производитель"
TRUNCATE TABLE Manufacturers CASCADE;
-- Удаление внешних ключей в таблице "Тип планеты"
TRUNCATE TABLE PlanetTypes CASCADE;
TRUNCATE TABLE Breakdowntypes CASCADE;
TRUNCATE TABLE Researchers CASCADE;
