package hou.tidaa.user.domain.factory;

import hou.tidaa.user.domain.model.User;

import java.util.UUID;

public class UserFactory {
    public static String generateUID(final String name) {
        return UUID.randomUUID().toString();
    }

    public static User register(final String name, final String password) {
        return new User(generateUID(name), name, password);
    }
}
