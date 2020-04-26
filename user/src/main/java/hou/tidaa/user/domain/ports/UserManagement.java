package hou.tidaa.user.domain.ports;

import hou.tidaa.user.domain.dto.UserRegisterDto;
import hou.tidaa.user.domain.model.User;

public interface UserManagement {
    User register(UserRegisterDto dto);
}