package niu.itmo.spaceresearch.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author amifideles
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StationIdDto {
    public Integer sourceStationId;
    public Integer destinationStationId;
}
