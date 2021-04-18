package az.turbo.backend.users.application.dto;

import az.turbo.backend.configs.MessageConstants;
import az.turbo.backend.shared.audited.AuditedCreateDto;
import az.turbo.backend.users.domain.model.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserSignUpDto extends AuditedCreateDto {
    @NotBlank(message = "First name is required!")
    private String firstName;

    @NotBlank(message = "Last name is required!")
    private String lastName;

    @NotNull(message = "Gender is required!")
    private Gender gender;

    @NotBlank(message = "Email is required!")
    @Email(message = MessageConstants.INVALID_EMAIL_FORMAT)
    private String email;

    private String mobileNumber;

    @NotNull(message = "Password is required!")
    @Length(min = 6, max = 20, message = "Password must 6-20 symbols!")
    private String password;
}
