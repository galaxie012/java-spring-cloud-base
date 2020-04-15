package hou.tidaa.user.domain.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserFactory {
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserFactory(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    private String generateUID(String name) {
        return UUID.randomUUID().toString();
    }

    public User register(String name, String password) {
        return new User(generateUID(name), name, passwordEncoder.encode(password));
    }
}
