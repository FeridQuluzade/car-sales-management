package az.turbo.backend.cities.domain.model;

import az.turbo.backend.shared.BaseEntity;

public class City extends BaseEntity {
    private long id;
    private String name;

    public City() {
    }

    public City(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
