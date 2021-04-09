package az.turbo.backend.bodytypes.application.dto;

import az.turbo.backend.shared.AuditCreateDto;

public class BodyCreateDto extends AuditCreateDto {

    private String name;

    public BodyCreateDto() {
    }

    public BodyCreateDto(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
