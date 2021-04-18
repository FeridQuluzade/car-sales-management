package az.turbo.backend.colors.application.dto;

public class ColorDto {
    private long id;
    private String name;

    public ColorDto() {
    }

    public ColorDto(long id, String name) {
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
