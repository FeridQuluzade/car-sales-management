package az.turbo.backend.users.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserTokenDto {
    private String tokenType;
    private String accessToken;
    private String refreshToken;
    private UserSignedInDto user;
}
