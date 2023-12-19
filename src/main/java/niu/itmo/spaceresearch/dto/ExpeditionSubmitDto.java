package niu.itmo.spaceresearch.dto;

import lombok.Data;

import java.util.List;

/**
 * @author amifideles
 */
@Data
public class ExpeditionSubmitDto {
    private Integer sourceStationId;
    private Integer destinationStationId;
    private Integer spaceshipId;
    private List<Integer> selectedResearchers;
}
