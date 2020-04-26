package hou.tidaa.user.domain.ports;

import hou.tidaa.core.security.token.IdentityDto;
import hou.tidaa.user.domain.dto.UsernamePasswordDto;

import java.util.Optional;

public interface UserAuthentication {
    Optional<IdentityDto> authentication(UsernamePasswordDto dto);
}
