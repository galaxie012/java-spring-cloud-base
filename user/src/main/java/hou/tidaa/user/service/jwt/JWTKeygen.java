package hou.tidaa.user.service.jwt;

import java.security.*;
import java.security.spec.EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class JWTKeygen {
    public static void main(String[] argv) throws Exception {
        try {
            // creating the object of KeyPairGenerator
            KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");

            // initializing with 1024
            kpg.initialize(1024);

            // getting key pairs
            // using generateKeyPair() method
            KeyPair kp = kpg.genKeyPair();

            // getting key
            PublicKey prv = kp.getPublic();
            PrivateKey pri = kp.getPrivate();

            // getting byte data of key
            byte[] publicKeyBytes = prv.getEncoded();
            byte[] privateKeyBytes = pri.getEncoded();

            System.out.println("Public Key");
            System.out.println(Base64.getEncoder().encodeToString(publicKeyBytes));
            System.out.println("Private Key");
            System.out.println(Base64.getEncoder().encodeToString(privateKeyBytes));

            // creating keySpec object
            EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(publicKeyBytes);

            // creating object of keyFactory
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");

            // generating Public key from the provided key spec.
            // using generatePublic() method
            PublicKey publicKey = keyFactory.generatePublic(publicKeySpec);

            // printing public key
            System.out.println("public key : " + publicKey);
        } catch (NoSuchAlgorithmException e) {

            System.out.println("Exception thrown : " + e);
        } catch (ProviderException e) {

            System.out.println("Exception thrown : " + e);
        }
    }
}
