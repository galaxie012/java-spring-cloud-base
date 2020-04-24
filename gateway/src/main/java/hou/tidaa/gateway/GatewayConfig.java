package hou.tidaa.gateway;


import hou.tidaa.core.security.jwt.JWTVerifyService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@Configuration
public class GatewayConfig {
    @Bean
    public JWTVerifyService tokenVerifier(@Value("${jwt.keys.rsa.public}") String base64EncodedPublicKey)
            throws InvalidKeySpecException, NoSuchAlgorithmException {

        return JWTVerifyService.fromPubliceKey(base64EncodedPublicKey);
    }
}
