package az.turbo.backend.colors.application.dto;

import az.turbo.backend.shared.AuditedUpdateDto;

public class ColorUpdateDto extends AuditedUpdateDto {
    private long id;
    private String name;

    public ColorUpdateDto() {
    }

    public ColorUpdateDto(long id, String name) {
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
