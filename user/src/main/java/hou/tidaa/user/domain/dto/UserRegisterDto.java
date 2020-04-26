package hou.tidaa.user.domain.dto;

import javax.validation.constraints.NotNull;

public class UserRegisterDto {
    @NotNull
    public String name;
    @NotNull
    public String password;
}
