package hou.tidaa.user.service;

import hou.tidaa.user.adapter.db.UserRepository;
import hou.tidaa.user.domain.dto.LoginResponse;
import hou.tidaa.user.domain.dto.UserLoginDto;
import hou.tidaa.user.domain.model.User;
import hou.tidaa.user.service.jwt.JWTSignerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class LoginService {
    private final JWTSignerFactory tokenSigner;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Autowired
    public LoginService(JWTSignerFactory tokenSigner, PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.tokenSigner = tokenSigner;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public LoginResponse login(UserLoginDto dto) throws UserLoginException {
        User user = userRepository.findByName(dto.name)
                .orElseThrow(() -> new UserLoginException("login failed."));

        if (passwordEncoder.matches(dto.password, user.getPassword())) {
            Map<String, String> claims = new HashMap<String, String>() {{
                put("uid", user.getId());
            }};
            return new LoginResponse(tokenSigner.sign(claims));
        } else {
            throw new UserLoginException("login failed.");
        }
    }
}
