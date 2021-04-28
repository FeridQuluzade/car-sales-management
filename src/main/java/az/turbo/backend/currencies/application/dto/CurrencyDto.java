package az.turbo.backend.currencies.application.dto;

public class CurrencyDto {
    private long id;
    private String name;

    public CurrencyDto() {
    }

    public CurrencyDto(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public void setName(String name) {
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
}
