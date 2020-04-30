package hou.tidaa.core.security.key;

import java.security.NoSuchAlgorithmException;

public class RSAKeyFactoryTest {
    public static void main(String[] argv) {
        try {
            RSAKeyFactory.Base64EncodeKeyPair keyPair = RSAKeyFactory.genKeyPair();

            System.out.println("Private Key");
            System.out.println(keyPair.getPrivateKey());

            System.out.println("public Key");
            System.out.println(keyPair.getPublicKey());
        }catch (NoSuchAlgorithmException e) {
            System.out.println("Exception thrown : " + e);
        }

    }
}
