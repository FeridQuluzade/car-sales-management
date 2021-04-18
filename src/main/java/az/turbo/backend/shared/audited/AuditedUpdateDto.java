package az.turbo.backend.shared.audited;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;

public class AuditedUpdateDto {
    @JsonIgnore
    private long updatedBy;

    @JsonIgnore
    private LocalDateTime updatedDate;

    public long getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(long updatedBy) {
        this.updatedBy = updatedBy;
    }

    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }
}
