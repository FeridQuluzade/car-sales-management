package az.turbo.backend.shared;

import java.time.LocalDateTime;

public class BaseEntity {
    private long createdBy;
    private LocalDateTime createdDate;
    private long updatedBy;
    private LocalDateTime updatedDate;
    private boolean isDeleted;
    private long deletedBy;
    private LocalDateTime deletedDate;
}
