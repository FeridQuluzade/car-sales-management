package az.turbo.backend.engineVolumes.application.dto;

import az.turbo.backend.shared.AuditCreateDto;

public class EngineVolumeCreateDto extends AuditCreateDto {
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
