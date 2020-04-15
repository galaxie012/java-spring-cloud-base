package hou.tidaa.user.adapter.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserEntityPersistence extends JpaRepository<UserEntity, String> {
    Optional<UserEntity> findByName(String name);

    @Transactional
    void deleteByName(String name);
}
