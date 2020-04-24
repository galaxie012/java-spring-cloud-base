package hou.tidaa.core.security.jwt;

import com.auth0.jwt.algorithms.Algorithm;
import hou.tidaa.core.security.key.RSAKeyFactory;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class JWTAlgorithmFactory {

//    public static Algorithm getJWTSignAlgorithm(String base64EncodePrivateKey)
//            throws InvalidKeySpecException, NoSuchAlgorithmException {
//        return Algorithm.RSA256(null, RSAKeyFactory.decodePrivateKey(base64EncodePrivateKey));
//    };

    public static Algorithm getJWTVerifyAlgorithm(String base64EncodedPublicKey)
        throws InvalidKeySpecException, NoSuchAlgorithmException {
        return Algorithm.RSA256(RSAKeyFactory.decodePublicKey(base64EncodedPublicKey), null);
    };


}
