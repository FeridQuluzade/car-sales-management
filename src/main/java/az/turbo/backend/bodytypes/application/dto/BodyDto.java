package az.turbo.backend.bodytypes.application.dto;

public class BodyDto {
    private long id;
    private String name;


    public BodyDto() {
    }

    public BodyDto(long id, String name) {
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
        return "BodyDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
