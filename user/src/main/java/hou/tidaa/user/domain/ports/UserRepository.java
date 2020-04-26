package hou.tidaa.user.domain.ports;

import hou.tidaa.user.domain.model.User;

import java.util.Optional;

public interface UserRepository {
    void save(User user);

    Optional<User> findById(String uid);

    Optional<User> findByName(String name);

    void deleteByName(String name);

    long count();
}
