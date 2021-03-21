package az.turbo.backend.customers.domain.model;

import az.turbo.backend.shared.BaseEntity;

public class Customer extends BaseEntity {
    private long id;
    private String fullName;
    private String phone;
    private String email;
}
