package az.turbo.backend.supplies.application.dto;

import az.turbo.backend.shared.audited.AuditedCreateDto;

public class SupplyCreateDto extends AuditedCreateDto {
    private String name;

    public SupplyCreateDto() {
    }

    public SupplyCreateDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
