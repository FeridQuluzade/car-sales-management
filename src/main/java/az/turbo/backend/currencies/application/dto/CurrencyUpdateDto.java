package az.turbo.backend.currencies.application.dto;

import az.turbo.backend.shared.audited.AuditedUpdateDto;

public class CurrencyUpdateDto extends AuditedUpdateDto {

    private long id;
    private String name;

    public CurrencyUpdateDto() {
    }

    public CurrencyUpdateDto(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
