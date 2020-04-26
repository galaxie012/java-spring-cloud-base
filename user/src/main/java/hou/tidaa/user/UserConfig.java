package hou.tidaa.user;

import hou.tidaa.core.security.jwt.JWTSignService;
import hou.tidaa.core.security.token.TokenSignService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;


@Configuration
public class UserConfig {
    @Bean
    public PasswordEncoder createPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public TokenSignService<String> TokenSigner(@Value("${jwt.keys.rsa.private}") final String base64EncodedPrivateKey)
            throws InvalidKeySpecException, NoSuchAlgorithmException {
        return JWTSignService.fromPrivateKey(base64EncodedPrivateKey);
    }
}
