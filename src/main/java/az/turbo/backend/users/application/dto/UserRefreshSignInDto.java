package az.turbo.backend.users.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRefreshSignInDto {
    @NotNull(message = "Refresh Token is required!")
    private String refreshToken;
}
