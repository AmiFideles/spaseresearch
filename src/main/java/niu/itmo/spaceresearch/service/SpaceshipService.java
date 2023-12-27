package niu.itmo.spaceresearch.service;

import lombok.RequiredArgsConstructor;
import niu.itmo.spaceresearch.dto.response.spaceship.DetailedSpaceshipDto;
import niu.itmo.spaceresearch.mapper.SpaceshipMapper;
import niu.itmo.spaceresearch.model.*;
import niu.itmo.spaceresearch.repository.ResearcherRepository;
import niu.itmo.spaceresearch.repository.SpaceshipRepository;
import niu.itmo.spaceresearch.repository.StationRepository;
import niu.itmo.spaceresearch.service.exceptions.EntityNotFoundException;
import niu.itmo.spaceresearch.service.exceptions.ResearcherNotFound;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author amifideles
 */

@RequiredArgsConstructor
@Service
public class SpaceshipService {
    private final SpaceshipRepository spaceshipRepository;
    private final StationService stationService;
    private final ResearcherRepository researcherRepository;
    public DetailedSpaceshipDto getSpaceshipById(Integer id, Principal principal) {
        Researcher researcher = researcherRepository.findByUsername(principal.getName())
                .orElseThrow(() -> new ResearcherNotFound(
                        "Researcher not found with username: %s"
                                .formatted(principal.getName())));
        Spaceship spaceship = spaceshipRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Spaceship not found with ID: " + id));
        List<Cabins> cabins = spaceship.getCabins();

        List<Cabins> accessibleCabins = cabins.stream()
                .filter(cabin -> !cabin.getLimitedAccess() || hasMatchingProfession(cabin, researcher))
                .collect(Collectors.toList());

        spaceship.setCabins(accessibleCabins);
        return SpaceshipMapper.toDetailedSpaceshipDto(spaceship);
    }

    private boolean hasMatchingProfession(Cabins cabin, Researcher researcher) {
        Set<Profession> researcherProfessions = researcher.getProfessions();
        return cabin.getProfessions().stream()
                .anyMatch(researcherProfessions::contains);
    }

    public List<DetailedSpaceshipDto> getSuitableSpaceships(Integer sourceStationId, Integer destinationStationId) {
        List<Spaceship> availableShips = spaceshipRepository.findAvailableShips(sourceStationId, destinationStationId);
        return SpaceshipMapper.toListDetailedSpaceshipDtoList(availableShips);
    }

//    public List<DetailedSpaceshipDto> getAvailableShips(Integer sourceStationId, Integer destinationStationId) {
//        // Получение информации о станциях и планетах
//        Station sourceStation = stationService.getStationById(sourceStationId);
//        Station destinationStation = stationService.getStationById(destinationStationId);
//        Planet sourcePlanet = sourceStation.getPlanet();
//        Planet destinationPlanet = destinationStation.getPlanet();
//
//        // Получение типа планеты назначения
//        PlanetType destinationPlanetType = destinationPlanet.getPlanetType();
//
//        // Получение свободных кораблей, соответствующих условиям
//        List<Spaceship> availableShips = getAllSpaceships()
//                .stream()
//                .filter(spaceship ->
//                        !spaceship.isInExpedition() &&
//                                spaceship.getCurrentStation().getId().equals(sourceStationId) &&
//                                spaceship.getPlanetTypes().contains(destinationPlanetType)
//                )
//                .collect(Collectors.toList());
//
//        // Маппинг в Dto
//        return availableShips.stream()
//                .map(this::mapToDetailedSpaceshipDto)
//                .collect(Collectors.toList());
//    }


}
