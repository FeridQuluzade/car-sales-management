package az.turbo.backend.currencies.application.dto;

import az.turbo.backend.shared.audited.AuditedCreateDto;

public class CurrencyCreateDto extends AuditedCreateDto {
    private String name;

    public CurrencyCreateDto() {
    }

    public CurrencyCreateDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
