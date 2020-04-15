package hou.tidaa.user.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class User {
    private final String id;
    private final String name;
    private String password;
}
