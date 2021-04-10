package az.turbo.backend.bodytypes.application.dto;

public class BodyTypeDto {
    private long id;
    private String name;

    public BodyTypeDto() {
    }

    public BodyTypeDto(long id, String name) {
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
