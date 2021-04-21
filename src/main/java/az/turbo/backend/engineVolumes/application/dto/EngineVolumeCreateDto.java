package az.turbo.backend.engineVolumes.application.dto;

import az.turbo.backend.shared.audited.AuditedCreateDto;

public class EngineVolumeCreateDto extends AuditedCreateDto {
    private String value;

    public EngineVolumeCreateDto() {
    }

    public EngineVolumeCreateDto(String name) {
        this.value = name;
    }

    public String getName() {
        return value;
    }

    public void setName(String name) {
        this.value = name;
    }
}
