package niu.itmo.spaceresearch.mapper;

import niu.itmo.spaceresearch.dto.ManufacturerDto;
import niu.itmo.spaceresearch.model.Manufacturer;

/**
 * @author amifideles
 */
public class ManufacturerMapper {
    public static ManufacturerDto toManufacturerDto(Manufacturer manufacturer) {
        return ManufacturerDto.builder()
                .id(manufacturer.getId())
                .name(manufacturer.getName())
                .country(manufacturer.getCountry())
                .build();
    }
}
