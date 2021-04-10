package az.turbo.backend.bodytypes.domain.model;

import az.turbo.backend.shared.BaseEntity;

public class BodyType extends BaseEntity {
    private long id;
    private String name;

    public BodyType() {
    }

    public BodyType(long id, String name) {
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
