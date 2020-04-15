package hou.tidaa.user.service.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import hou.tidaa.user.UserConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.*;
import java.security.spec.*;
import java.util.HashMap;
import java.util.Map;

@Component
public class JWTSignerFactory {
    private final Algorithm algorithm;

    @Autowired
    public JWTSignerFactory(Algorithm algorithm) {
        this.algorithm = algorithm;
    }

    public String sign(Map<String, String> payloadClaims) {
        JWTCreator.Builder signer = JWT.create();
        for (Map.Entry<String, String> e : payloadClaims.entrySet()) {
            signer.withClaim(e.getKey(), e.getValue());
        }
        return signer.sign(algorithm);
    }

    public static void main(String[] argv)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        String publicKey =  "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCblSRvnbwpHplZRwqgSAH98gj/I9PF0HMDM7KSlJ4fnjxJufApZgDoMm8HReM+7WAcYJrDCuG/dUvNT4jbid5jIX2GW/1yPy8Pic3+PjKHsuQHSF7bsT88mHsjwq6kKQzeuwZfBk2vilBFK0k2q8Lxt0a5ksOYHDS/dNmC4wrJ+QIDAQAB";
        String privateKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAJuVJG+dvCkemVlHCqBIAf3yCP8j08XQcwMzspKUnh+ePEm58ClmAOgybwdF4z7tYBxgmsMK4b91S81PiNuJ3mMhfYZb/XI/Lw+Jzf4+Moey5AdIXtuxPzyYeyPCrqQpDN67Bl8GTa+KUEUrSTarwvG3RrmSw5gcNL902YLjCsn5AgMBAAECgYEAjUxmSvh2auHfY7WhcO/03y1x/kYLjxxBWCcbhwRyl0SefFbiRrbdHASx0WkneX8cSqlx5LF9VOSyc0RIhxovXWXOzokSyclut/f1sm0vxC87grdaZipkUInstoWXO8tLe6OozQbeyWPOdCwH1ux38t1LTkdsr5pm8nc3LJSZ2gECQQD25fuMSu5Mw5p5EvOKqVOfX5rWPVWlee4HzHWuq9rrTqZy4M5vVTWbRvU5YMalv+hV2c4c4zdCcffO+j3wU9YvAkEAoVFmGQz/a9h1LJyUdgECHoeDEwm5ObIPVGkI+c0aTAaV8hsrouTcvnyL+i7kyan9c+BYYueS60f0YxP3jAMAVwJBAJrzFv2jAQ84KzgVBrddMVfyPlB6ScgCT7qhPLIUtN28jIXIRIZmqa/7xmbuL31ShVNr0AkdKE8RC37gWgL7+o0CQEjyyjyhyA84vyQBWpFSO2ca2IcPapdQkeYV6V0tBqtCfyQQbVcoLzpkj9f+xK79FAQGe3GeTq3eyADV0qeZ49kCQCTz+jV4xyh2YCAo/rWwoWBpS2E3CT++olvtdGf1SsHYN3RERVJB4ggJOqK96XDBA63oo8S5Ee7Bx91cKIv50A8=";

        Map<String, String> claims = new HashMap<String, String>() {{
            put("uid", "user-uuid");
        }};

        String token = new JWTSignerFactory(new UserConfig().getJWTSignAlgorithm(privateKey)).sign(claims);
        System.out.println(token);
    }
}
