package niu.itmo.spaceresearch.service;

import lombok.RequiredArgsConstructor;
import niu.itmo.spaceresearch.dto.BreakdownTypeDto;
import niu.itmo.spaceresearch.mapper.BreakdownTypeMapper;
import niu.itmo.spaceresearch.repository.BreakdownTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author amifideles
 */
@RequiredArgsConstructor
@Service
public class BreakdownTypeService {
    private final BreakdownTypeRepository breakdownTypeRepository;

    public List<BreakdownTypeDto> getAllBreakdownTypes(){
        return BreakdownTypeMapper.toListBreakdownTypeDto(breakdownTypeRepository.findAll());
    }
}
