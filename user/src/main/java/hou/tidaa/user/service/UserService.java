package hou.tidaa.user.service;

import hou.tidaa.core.security.token.IdentityDto;
import hou.tidaa.user.domain.dto.UserRegisterDto;
import hou.tidaa.user.domain.dto.UsernamePasswordDto;
import hou.tidaa.user.domain.exception.RegistrationException;
import hou.tidaa.user.domain.factory.UserFactory;
import hou.tidaa.user.domain.model.User;
import hou.tidaa.user.domain.ports.UserAuthentication;
import hou.tidaa.user.domain.ports.UserManagement;
import hou.tidaa.user.domain.ports.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserService implements UserManagement, UserAuthentication {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(final UserRepository userRepository, final PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public Optional<IdentityDto> authentication(final UsernamePasswordDto dto) {
        return userRepository.findByName(dto.username)
                .filter(user -> passwordEncoder.matches(dto.password, user.getPassword()))
                .map(user -> new IdentityDto(user.getId()));
    }

    @Override
    public User register(final UserRegisterDto dto) {
        userRepository.findByName(dto.name)
                .ifPresent(user -> {
                    throw new RegistrationException("User name already exist! Please choose another user name.");
                });

        final String password = passwordEncoder.encode(dto.password);
        final User user = UserFactory.register(dto.name, password);
        userRepository.save(user);
        return user;
    }
}
