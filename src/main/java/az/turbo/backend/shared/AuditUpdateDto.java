package az.turbo.backend.shared;

import java.time.LocalDateTime;

public class AuditUpdateDto {
    private long updatedBy;
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
