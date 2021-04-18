package az.turbo.backend.engineVolumes.application.dto;

import az.turbo.backend.shared.audited.AuditedCreateDto;

public class EngineVolumeCreateDto extends AuditedCreateDto {
    private String name;

    public EngineVolumeCreateDto() {
    }

    public EngineVolumeCreateDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
