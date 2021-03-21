package az.turbo.backend.users.domain.model;

import az.turbo.backend.shared.BaseEntity;

public class User extends BaseEntity {
    private long id;
    private String firstName;
    private String lastName;
    private Gender gender;
    private String email;
    private String password;
}
