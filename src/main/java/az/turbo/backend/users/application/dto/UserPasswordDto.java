package az.turbo.backend.users.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserPasswordDto {
    private long id;
    private String role;
    private String imageUrl;
    private String firstName;
    private String lastName;
    private String gender;
    private String email;
    private boolean isEmailConfirmed;
    private String mobileNumber;
    private String password;
}
