package az.turbo.backend.bodytypes.application.dto;

import az.turbo.backend.shared.AuditUpdateDto;

public class BodyTypeUpdateDto extends AuditUpdateDto {
   private long id;
   private String name;

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
