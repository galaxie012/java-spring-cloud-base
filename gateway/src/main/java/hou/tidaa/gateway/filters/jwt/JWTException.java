package hou.tidaa.gateway.filters.jwt;

public class JWTException extends Exception {
    public static final String AUTHORIZATION_HEADER_IS_MISSING = "Authorization header is missing";
    public static final String AUTHORIZATION_CONTENT_IS_MISSING = "Authorization content is missing";
    public static final String MALFORMED_AUTHORIZATION_CONTENT = "Malformed authorization content";
    public static final String BEARER_IS_NEEDED = "Bearer is needed";

    public JWTException(String message) {
        super(message);
    }
}