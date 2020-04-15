package hou.tidaa.user.adapter.db;

import hou.tidaa.user.domain.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepository {
    private static final ModelMapper mapper = new ModelMapper();
    private final UserEntityPersistence userEntityPersistence;

    @Autowired
    public UserRepository(UserEntityPersistence userEntityPersistence) {
        this.userEntityPersistence = userEntityPersistence;
    }

    public void save(User user) {
        UserEntity entity = mapper.map(user, UserEntity.class);
        userEntityPersistence.save(entity);
    }

    public Optional<User> findById(String uid) {
        return userEntityPersistence.findById(uid)
                .map(entity -> new User(entity.getId(), entity.getName(), entity.getPassword()));
    }

    public Optional<User> findByName(String name) {
        return userEntityPersistence.findByName(name)
                .map(entity -> new User(entity.getId(), entity.getName(), entity.getPassword()));
    }

    public void deleteByName(String name) {
        userEntityPersistence.deleteByName(name);
    }

    public long count() {
        return userEntityPersistence.count();
    }
}
