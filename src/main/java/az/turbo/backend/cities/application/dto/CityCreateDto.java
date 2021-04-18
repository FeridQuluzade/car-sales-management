package az.turbo.backend.cities.application.dto;

import az.turbo.backend.shared.audited.AuditedCreateDto;

public class CityCreateDto extends AuditedCreateDto {
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
