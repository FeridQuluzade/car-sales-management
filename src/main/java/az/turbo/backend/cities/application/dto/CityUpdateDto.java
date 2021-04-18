package az.turbo.backend.cities.application.dto;

import az.turbo.backend.shared.AuditedUpdateDto;

public class CityUpdateDto extends AuditedUpdateDto {
    private long id;
    private String name;

    public CityUpdateDto() {
    }

    public CityUpdateDto(long id, String name) {
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

    @Override
    public String toString() {
        return "CityUpdateDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
