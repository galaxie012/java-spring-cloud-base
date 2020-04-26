package hou.tidaa.user.domain.exception;

public class RegistrationException extends RuntimeException{
    public RegistrationException(final String message) {
        super(message);
    }
}
