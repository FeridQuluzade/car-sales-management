package az.turbo.backend.users.application.dto;

import az.turbo.backend.shared.audited.AuditedUpdateDto;
import az.turbo.backend.users.domain.model.Gender;

public class UserUpdateDto extends AuditedUpdateDto {
    private long id;
    private String firstName;
    private String lastName;
    private Gender gender;
    private String email;


    public String getFirstName() {
        return firstName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

}
