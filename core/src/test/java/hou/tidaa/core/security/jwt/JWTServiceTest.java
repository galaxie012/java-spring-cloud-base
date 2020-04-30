package hou.tidaa.core.security.jwt;

import hou.tidaa.core.security.token.IdentityDto;
import hou.tidaa.core.security.token.Token;
import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class JWTServiceTest {
    private final String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCblSRvnbwpHplZRwqgSAH98gj/I9PF0HMDM7KSlJ4fnjxJufApZgDoMm8HReM+7WAcYJrDCuG/dUvNT4jbid5jIX2GW/1yPy8Pic3+PjKHsuQHSF7bsT88mHsjwq6kKQzeuwZfBk2vilBFK0k2q8Lxt0a5ksOYHDS/dNmC4wrJ+QIDAQAB";
    private final String privateKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAJuVJG+dvCkemVlHCqBIAf3yCP8j08XQcwMzspKUnh+ePEm58ClmAOgybwdF4z7tYBxgmsMK4b91S81PiNuJ3mMhfYZb/XI/Lw+Jzf4+Moey5AdIXtuxPzyYeyPCrqQpDN67Bl8GTa+KUEUrSTarwvG3RrmSw5gcNL902YLjCsn5AgMBAAECgYEAjUxmSvh2auHfY7WhcO/03y1x/kYLjxxBWCcbhwRyl0SefFbiRrbdHASx0WkneX8cSqlx5LF9VOSyc0RIhxovXWXOzokSyclut/f1sm0vxC87grdaZipkUInstoWXO8tLe6OozQbeyWPOdCwH1ux38t1LTkdsr5pm8nc3LJSZ2gECQQD25fuMSu5Mw5p5EvOKqVOfX5rWPVWlee4HzHWuq9rrTqZy4M5vVTWbRvU5YMalv+hV2c4c4zdCcffO+j3wU9YvAkEAoVFmGQz/a9h1LJyUdgECHoeDEwm5ObIPVGkI+c0aTAaV8hsrouTcvnyL+i7kyan9c+BYYueS60f0YxP3jAMAVwJBAJrzFv2jAQ84KzgVBrddMVfyPlB6ScgCT7qhPLIUtN28jIXIRIZmqa/7xmbuL31ShVNr0AkdKE8RC37gWgL7+o0CQEjyyjyhyA84vyQBWpFSO2ca2IcPapdQkeYV6V0tBqtCfyQQbVcoLzpkj9f+xK79FAQGe3GeTq3eyADV0qeZ49kCQCTz+jV4xyh2YCAo/rWwoWBpS2E3CT++olvtdGf1SsHYN3RERVJB4ggJOqK96XDBA63oo8S5Ee7Bx91cKIv50A8=";

    private final IdentityDto identity = new IdentityDto("uid");
    private final String jwt = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJ1aWQiOiJ1aWQifQ.RiCaCq8llxJBN8d50bb9FxhLwENJ6hj68kyADWTlyjabPIwQ2gnHZU7bhfxdQaPaQpHXPO7afv6ECRnkZyWbu1Ov32B5TNg3EeP9KtOYJIC_oBA5rTjU0e1GOpvOSyejw1uJK4iLFWCuCwvPxlkZNL-OurPNP1sNqAuisHfqN1g";
    private final Token<String> token = Token.jwt(jwt);

    @Test
    void sign() throws InvalidKeySpecException, NoSuchAlgorithmException {
        JWTSignService signService = new JWTSignService(JWTAlgorithmFactory.getJWTSignAlgorithm(privateKey));
        Token<String> signed = signService.sign(identity);
        assertEquals(signed, token);

    }

    @Test
    void verify() throws InvalidKeySpecException, NoSuchAlgorithmException {
        JWTVerifyService verifyService = new JWTVerifyService(JWTAlgorithmFactory.getJWTVerifyAlgorithm(publicKey));

        Optional<IdentityDto> dto = verifyService.verify(token);
        assertTrue(dto.isPresent());

        assertEquals(dto.get(), identity);
    }

    @Test
    void should_thrw_exception_when_key_invalid() {
        assertThrows(InvalidKeySpecException.class, () -> {
            JWTAlgorithmFactory.getJWTVerifyAlgorithm("");
        });
    }

}
