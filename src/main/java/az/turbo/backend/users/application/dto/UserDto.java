package az.turbo.backend.users.application.dto;

import az.turbo.backend.users.domain.model.Gender;

public class UserDto {
    private long id;
    private String firstName;
    private String lastName;
    private Gender gender;
    private String email;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Id = " + id +
                " First name = " + firstName +
                " Last name = " + lastName +
                " Gender = " + gender +
                " Email = " + email;
    }
}
