package az.turbo.backend.configs;

public class SecurityConstants {
    public static final String SECRET = "SecretKeyToGenJWTs";
    public static final long EXPIRATION_TIME = 900_000_000; // 15 minitues
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String USER_ID_PARAM = "userId";
    public static final String USER_AUTHORITY_PARAM = "authority";

    private SecurityConstants() {
    }
}