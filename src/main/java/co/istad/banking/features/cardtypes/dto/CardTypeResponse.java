package co.istad.banking.features.cardtypes.dto;

import lombok.Builder;

@Builder
public record CardTypeResponse(
        String name,

        Boolean isEnabled
) {
}
