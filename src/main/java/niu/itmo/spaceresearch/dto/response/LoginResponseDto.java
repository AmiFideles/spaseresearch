package niu.itmo.spaceresearch.dto.response;

import lombok.Builder;

/**
 * @author amifideles
 */
@Builder
public record LoginResponseDto(
        String credentials,
        Boolean isCapitan) {
}