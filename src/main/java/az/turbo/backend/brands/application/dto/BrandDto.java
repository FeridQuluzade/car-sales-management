package az.turbo.backend.brands.application.dto;

public class BrandDto {

    private long id;
    private String name;

    public BrandDto() {
    }

    public BrandDto(long id, String name) {
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
