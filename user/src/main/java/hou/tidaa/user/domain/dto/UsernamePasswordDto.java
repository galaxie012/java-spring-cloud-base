package hou.tidaa.user.domain.dto;

import javax.validation.constraints.NotNull;

public class UsernamePasswordDto {
    @NotNull
    public String username;
    @NotNull
    public String password;
}
