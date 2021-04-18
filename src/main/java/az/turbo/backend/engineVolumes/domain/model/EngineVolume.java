package az.turbo.backend.engineVolumes.domain.model;

import az.turbo.backend.shared.BaseEntity;

public class EngineVolume extends BaseEntity {
    private long id;
    private String value;

    public EngineVolume() {
    }

    public EngineVolume(long id, String value) {
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
