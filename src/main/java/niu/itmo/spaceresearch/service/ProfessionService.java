package niu.itmo.spaceresearch.service;

import lombok.RequiredArgsConstructor;
import niu.itmo.spaceresearch.dto.ProfessionDto;
import niu.itmo.spaceresearch.model.Profession;
import niu.itmo.spaceresearch.repository.ProfessionRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static niu.itmo.spaceresearch.mapper.ProfessionMapper.mapProfessionsToListDto;

/**
 * @author amifideles
 */
@RequiredArgsConstructor
@Service
public class ProfessionService {
    private final ProfessionRepository professionRepository;


    public List<ProfessionDto> getAllProfessions() {
        List<Profession> professions = professionRepository.findAll();
        Set<ProfessionDto> professionDtoSet = mapProfessionsToListDto(new HashSet<>(professions));
        return new ArrayList<>(professionDtoSet);
    }
}
