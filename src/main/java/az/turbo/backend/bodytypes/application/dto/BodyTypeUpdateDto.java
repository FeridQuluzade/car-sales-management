package az.turbo.backend.bodytypes.application.dto;

import az.turbo.backend.shared.audited.AuditedUpdateDto;

public class BodyTypeUpdateDto extends AuditedUpdateDto {
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
