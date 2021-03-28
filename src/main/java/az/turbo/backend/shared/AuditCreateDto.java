package az.turbo.backend.shared;

import java.time.LocalDateTime;

public class AuditCreateDto {
    private long createdBy;
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
