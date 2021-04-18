package az.turbo.backend.engineVolumes.application.dto;

import az.turbo.backend.shared.AuditUpdateDto;

public class EngineVolumeUpdateDto extends AuditUpdateDto {

    private long id;
    private String value;

    public EngineVolumeUpdateDto() {
    }

    public EngineVolumeUpdateDto(long id, String value) {
        this.id = id;
        this.value = value;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
