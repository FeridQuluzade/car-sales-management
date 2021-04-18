package az.turbo.backend.users.domain.model;

import az.turbo.backend.shared.BaseEntity;

public class User extends BaseEntity {
    private long id;
    private String firstName;
    private String lastName;
    private Gender gender;
    private Role role;
    private String email;
    private boolean isEmailConfirmed;
    private String password;
    private String refreshToken;

    public User() {}

    public User(long id,
                String firstName,
                String lastName,
                Gender gender,
                Role role,
                String email,
                Boolean isEmailConfirmed,
                String password,
                String refreshToken) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.role = role;
        this.email = email;
        this.isEmailConfirmed = isEmailConfirmed;
        this.password = password;
        this.refreshToken = refreshToken;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEmailConfirmed() {
        return isEmailConfirmed;
    }

    public void setEmailConfirmed(boolean emailConfirmed) {
        isEmailConfirmed = emailConfirmed;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
