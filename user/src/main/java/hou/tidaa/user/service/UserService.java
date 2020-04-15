package hou.tidaa.user.service;

import hou.tidaa.user.adapter.db.UserRepository;
import hou.tidaa.user.domain.dto.UserRegisteringDto;
import hou.tidaa.user.domain.model.User;
import hou.tidaa.user.domain.model.UserFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserFactory userFactory;

    @Autowired
    public UserService(UserRepository userRepository, UserFactory userFactory) {
        this.userRepository = userRepository;
        this.userFactory = userFactory;
    }

    public User findById(String uid) throws UserNotFoundException {
        return userRepository.findById(uid)
                .orElseThrow(() -> new UserNotFoundException("No user found with uid:" + uid));
    }

    public User saveUser(UserRegisteringDto dto) {
        Optional<User> optionalUser = userRepository.findByName(dto.name);
        if (optionalUser.isPresent()) {
            throw new UsernameAlreadyExistException("User name already exist! Please choose another user name.");
        }
        final User user = userFactory.register(dto.name, dto.password);
        userRepository.save(user);
        return user;
    }
}
