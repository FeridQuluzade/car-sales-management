package az.turbo.backend.configs;

import az.turbo.backend.shared.ApiError;
import az.turbo.backend.users.application.UserService;
import az.turbo.backend.users.application.dto.UserPasswordDto;
import az.turbo.backend.users.application.dto.UserSignInDto;
import az.turbo.backend.users.application.dto.UserSignedInDto;
import az.turbo.backend.users.application.dto.UserTokenDto;
import az.turbo.backend.users.application.exception.UserEmailNotConfirmedException;
import com.auth0.jwt.JWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;

@Slf4j
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws AuthenticationException {
        try {
            UserSignInDto userSignInDto = new ObjectMapper()
                    .readValue(req.getInputStream(), UserSignInDto.class);

            UserPasswordDto userPasswordDto = userService.retrieveByEmail(userSignInDto.getUsername());

            if (userPasswordDto == null) {
                throw new BadCredentialsException("Invalid username or password");
            }

            if(!userPasswordDto.isEmailConfirmed()) {
                throw new UserEmailNotConfirmedException("User email is not confirmed!");
            }

            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userSignInDto.getUsername(),
                            userSignInDto.getPassword(),
                            new ArrayList<>()));

            return auth;
        } catch (Exception e) {
            log.error("AuthFilter error", e);
            ApiError error = new ApiError(HttpStatus.FORBIDDEN);
            if (e instanceof BadCredentialsException) {
                error.setMessage("Invalid username or password");
            } else {
                error.setMessage(e.getMessage());
            }

            try {
                res.setStatus(error.getStatus().value());
                res.setHeader("Content-Type", "application/json");
                res.getWriter().write(SerializationUtil.convertObjectToJson(error));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain, Authentication auth) throws IOException {
        User user = (User) auth.getPrincipal();

        UserPasswordDto userPasswordDto = userService.retrieveByEmail(user.getUsername());

        String token = JWT.create()
                .withSubject(user.getUsername())
                .withClaim(SecurityConstants.USER_ID_PARAM, userPasswordDto.getId())
                .withClaim(SecurityConstants.USER_AUTHORITY_PARAM, userPasswordDto.getRole())
                .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
                .sign(HMAC512(SecurityConstants.SECRET.getBytes()));

        res.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + token);

        String refreshToken = UUID.randomUUID().toString();

        userService.updateRefreshToken(userPasswordDto.getId(), refreshToken);

        UserSignedInDto userSignedInDto = convertToSignedIn(userPasswordDto);

        UserTokenDto userTokenDto = UserTokenDto
                .builder()
                .tokenType("Bearer")
                .accessToken(token)
                .refreshToken(refreshToken)
                .user(userSignedInDto)
                .build();

        res.getWriter().write(SerializationUtil.convertObjectToJson(userTokenDto));

        res.setContentType("application/json");
    }

    private UserSignedInDto convertToSignedIn(UserPasswordDto userPasswordDto) {
        return UserSignedInDto
                .builder()
                .id(userPasswordDto.getId())
                .role(userPasswordDto.getRole())
                .imageUrl(userPasswordDto.getImageUrl())
                .firstName(userPasswordDto.getFirstName())
                .lastName(userPasswordDto.getLastName())
                .gender(userPasswordDto.getGender())
                .email(userPasswordDto.getEmail())
                .mobileNumber(userPasswordDto.getMobileNumber())
                .build();
    }
}
