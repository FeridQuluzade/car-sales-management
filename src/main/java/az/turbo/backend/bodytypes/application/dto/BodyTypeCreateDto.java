package az.turbo.backend.bodytypes.application.dto;

import az.turbo.backend.shared.audited.AuditedCreateDto;

public class BodyTypeCreateDto extends AuditedCreateDto {

    private String name;

    public BodyTypeCreateDto() {
    }

    public BodyTypeCreateDto(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
