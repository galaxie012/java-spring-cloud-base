package hou.tidaa.core.security.key;

import lombok.Data;
import org.apache.commons.codec.binary.Base64;

import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.EncodedKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class RSAKeyFactory {
    public static Base64EncodeKeyPair genKeyPair() throws NoSuchAlgorithmException {
        //creating the object of KeyPairGenerator
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");

        kpg.initialize(1024);
        KeyPair kp = kpg.genKeyPair();

        // getting byte data of key
        byte[] publicKeyBytes = kp.getPublic().getEncoded();
        byte[] privateKeyBytes = kp.getPrivate().getEncoded();

        java.util.Base64.Encoder ec = java.util.Base64.getEncoder();
        return new Base64EncodeKeyPair(ec.encodeToString(privateKeyBytes),ec.encodeToString(publicKeyBytes));
    }

    public static RSAPrivateKey decodePrivateKey(String base64EncodedPrivateKey)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");

        byte[] privateKeyBytes = Base64.decodeBase64(base64EncodedPrivateKey);
        EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
        return (RSAPrivateKey) keyFactory.generatePrivate(keySpec);

    }

    public static RSAPublicKey decodePublicKey(String base64EncodedPublicKey)
            throws InvalidKeySpecException, NoSuchAlgorithmException {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");

        byte[] publicBytes = Base64.decodeBase64(base64EncodedPublicKey);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicBytes);
        return (RSAPublicKey) keyFactory.generatePublic(keySpec);
    }


    @Data
    public static class Base64EncodeKeyPair {
        private final String privateKey;
        private final String publicKey;
    }
}
