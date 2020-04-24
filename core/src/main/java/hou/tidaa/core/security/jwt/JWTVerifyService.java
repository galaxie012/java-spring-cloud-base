package hou.tidaa.core.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import hou.tidaa.core.security.token.IdentityDto;
import hou.tidaa.core.security.token.Token;
import hou.tidaa.core.security.token.TokenVerifyService;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Optional;

public class JWTVerifyService implements TokenVerifyService<String> {

    private final JWTVerifier verifier;

    public JWTVerifyService(Algorithm algorithm) {
        this.verifier = JWT.require(algorithm).build();
    }

    public static JWTVerifyService fromPubliceKey(String base64EncodedPublicKey)
        throws InvalidKeySpecException, NoSuchAlgorithmException{
        Algorithm algorithm = JWTAlgorithmFactory.getJWTVerifyAlgorithm(base64EncodedPublicKey);
        return new JWTVerifyService(algorithm);
    }

    @Override
    public Optional<IdentityDto> verify(Token<String> token) {
        DecodedJWT decodedJWT = this.verifier.verify(token.getToken());
        return Optional.of(new IdentityDto(
                decodedJWT.getClaim("uid").asString()));
    }
}
