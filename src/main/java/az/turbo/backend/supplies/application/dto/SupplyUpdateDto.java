package az.turbo.backend.supplies.application.dto;

import az.turbo.backend.shared.audited.AuditedUpdateDto;

public class SupplyUpdateDto extends AuditedUpdateDto {

    private long id;
    private String name;

    public SupplyUpdateDto() {
    }

    public SupplyUpdateDto(long id, String name) {
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
