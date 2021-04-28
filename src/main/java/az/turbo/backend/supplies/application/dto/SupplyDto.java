package az.turbo.backend.supplies.application.dto;

public class SupplyDto {
    private long id;
    private String name;

    public SupplyDto() {
    }

    public SupplyDto(long id, String name) {
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
