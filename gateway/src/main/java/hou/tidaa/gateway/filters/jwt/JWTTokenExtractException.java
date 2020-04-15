package hou.tidaa.gateway.filters.jwt;

import com.auth0.jwt.exceptions.JWTVerificationException;

public class JWTTokenExtractException extends JWTVerificationException {

    public JWTTokenExtractException(String message) {
        super(message);
    }
}
