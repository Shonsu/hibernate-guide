package pl.shonsu.hibernate.controller.dto;

public record CategoryDto(
        Long id,
        String name,
        String description
) {
}
