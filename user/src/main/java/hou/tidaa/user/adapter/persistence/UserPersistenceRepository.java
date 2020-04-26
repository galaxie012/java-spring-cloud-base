package hou.tidaa.user.adapter.persistence;

import hou.tidaa.user.domain.model.User;
import hou.tidaa.user.domain.ports.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserPersistenceRepository implements UserRepository {
    private static final ModelMapper MAPPER = new ModelMapper();

    private final UserEntityPersistence userEntityPersistence;

    @Autowired
    public UserPersistenceRepository(final UserEntityPersistence userEntityPersistence) {
        this.userEntityPersistence = userEntityPersistence;
    }

    @Override
    public void save(final User user) {
        final UserEntity entity = MAPPER.map(user, UserEntity.class);
        userEntityPersistence.save(entity);
    }

    @Override
    public Optional<User> findById(final String uid) {
        return userEntityPersistence.findById(uid)
                .map(userEntity -> new User(userEntity.getId(), userEntity.getName(), userEntity.getPassword()));
    }

    @Override
    public Optional<User> findByName(final String name) {
        return userEntityPersistence.findByName(name)
                .map(userEntity -> new User(userEntity.getId(), userEntity.getName(), userEntity.getPassword()));
    }

    @Override
    public void deleteByName(final String name) {
        userEntityPersistence.deleteByName(name);
    }

    @Override
    public long count() {
        return userEntityPersistence.count();
    }
}
