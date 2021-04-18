package az.turbo.backend.colors.application.dto;

import az.turbo.backend.shared.AuditedCreateDto;

public class ColorCreateDto extends AuditedCreateDto {
    private String name;

    public ColorCreateDto() {
    }

    public ColorCreateDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
