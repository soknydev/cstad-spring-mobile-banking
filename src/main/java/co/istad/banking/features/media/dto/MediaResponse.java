package co.istad.banking.features.media.dto;

import lombok.Builder;

@Builder
public record MediaResponse(
        String name, // unique
        String contextType, // png, jpeg, mp4
        String extension,
        String uri,
        Long size
) {
}
