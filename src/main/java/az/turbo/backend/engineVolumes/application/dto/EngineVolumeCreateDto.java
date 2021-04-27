package az.turbo.backend.engineVolumes.application.dto;

import az.turbo.backend.shared.audited.AuditedCreateDto;

public class EngineVolumeCreateDto extends AuditedCreateDto {
    private String value;

    public EngineVolumeCreateDto() {
    }

    public EngineVolumeCreateDto(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
