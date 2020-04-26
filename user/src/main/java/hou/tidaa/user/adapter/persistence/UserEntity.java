package hou.tidaa.user.adapter.persistence;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "users")
@Data
@NoArgsConstructor
public class UserEntity {
    @Id
    private String id;
    private String name;
    private String password;
}
