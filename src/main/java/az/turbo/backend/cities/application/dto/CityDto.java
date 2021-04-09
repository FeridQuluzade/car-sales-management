package az.turbo.backend.cities.application.dto;

public class CityDto {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "CityDto{" +
                "name='" + name + '\'' +
                '}';
    }
}
