package az.turbo.backend.shared.audited;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;

public class AuditedCreateDto {
    @JsonIgnore
    private long createdBy;

    @JsonIgnore
    private LocalDateTime createdDate;

    public long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(long createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }
}
