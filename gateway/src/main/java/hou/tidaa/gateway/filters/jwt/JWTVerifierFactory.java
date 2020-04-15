package hou.tidaa.gateway.filters.jwt;

import com.auth0.jwk.Jwk;
import com.auth0.jwk.JwkException;
import com.auth0.jwk.UrlJwkProvider;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

@Slf4j
@Component
public class JWTVerifierFactory {
    @Bean
    @Lazy
    @Qualifier("jwk")
    public JWTVerifier verifyTokenUseJwkProvider(@Value("${jwt.issuer}") String issuer, @Value("${jwt.audience}") String audience)
            throws JwkException, IOException {
        log.debug("create jwk JWTVerifier");

        UrlJwkProvider urlJwkProvider = new UrlJwkProvider(issuer);
        RestTemplate restTemplate = new RestTemplate();

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(restTemplate.getForObject(issuer + "/.well-known/jwks.json", String.class));
        String kid = jsonNode.get("keys").get(0).get("kid").asText();

        Jwk jwk = urlJwkProvider.get(kid);

        JWTVerifier verifier = JWT.require(Algorithm.RSA256((RSAPublicKey) jwk.getPublicKey(), null))
                .withIssuer(issuer)
                .withAudience(audience)
                .build();
        return verifier;
    }

    @Bean
    @Qualifier("jwt")
    public JWTVerifier verifyTokenUsePublicKey(@Value("${jwt.key}") String publicKey)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        log.debug("create jwt JWTVerifier");

        byte[] publicBytes = Base64.decodeBase64(publicKey);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey pubKey = keyFactory.generatePublic(keySpec);

        JWTVerifier verifier = JWT.require(Algorithm.RSA256((RSAPublicKey) pubKey, null))
                .build();
        return verifier;
    }
}
