package hou.tidaa.core.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import hou.tidaa.core.security.token.IdentityDto;
import hou.tidaa.core.security.token.Token;
import hou.tidaa.core.security.token.TokenSignService;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class JWTSignService implements TokenSignService<String> {
    private final Algorithm algorithm;

    public JWTSignService(Algorithm algorithm) {
        this.algorithm = algorithm;
    }

    public static JWTSignService fromPrivateKey(String base64EncodedPrivateKey)
            throws InvalidKeySpecException, NoSuchAlgorithmException {
        Algorithm algorithm = JWTAlgorithmFactory.getJWTSignAlgorithm(base64EncodedPrivateKey);
        return new JWTSignService(algorithm);
    }

    @Override
    public Token<String> sign(IdentityDto auth) {
        return Token.jwt(
                JWT.create()
                .withClaim("uid",auth.getUid())
                .sign(algorithm)
        );
    }
}