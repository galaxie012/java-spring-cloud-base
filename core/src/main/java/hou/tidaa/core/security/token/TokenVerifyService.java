package hou.tidaa.core.security.token;

import java.util.Optional;

public interface TokenVerifyService<T> {
    Optional<IdentityDto> verify(Token<T> token);
}
