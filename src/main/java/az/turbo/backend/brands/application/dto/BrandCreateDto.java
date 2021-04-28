package az.turbo.backend.brands.application.dto;

import az.turbo.backend.shared.audited.AuditedCreateDto;

public class BrandCreateDto extends AuditedCreateDto {
    private String name;

    public BrandCreateDto() {
    }

    public BrandCreateDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
