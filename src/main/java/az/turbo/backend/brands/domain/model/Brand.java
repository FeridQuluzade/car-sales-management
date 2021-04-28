package az.turbo.backend.brands.domain.model;

import az.turbo.backend.shared.BaseEntity;

public class Brand extends BaseEntity {
    private long id;
    private String name;

    public Brand() {
    }

    public Brand(long id, String name) {
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
