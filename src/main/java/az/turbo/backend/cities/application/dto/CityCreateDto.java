package az.turbo.backend.cities.application.dto;

import az.turbo.backend.shared.AuditCreateDto;

public class CityCreateDto extends AuditCreateDto {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "CityCreateDto{" +
                "name='" + name + '\'' +
                '}';
    }
}
