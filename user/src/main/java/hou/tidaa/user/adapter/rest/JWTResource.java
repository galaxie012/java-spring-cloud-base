package hou.tidaa.user.adapter.rest;

import hou.tidaa.core.security.token.IdentityDto;
import hou.tidaa.core.security.token.Token;
import hou.tidaa.core.security.token.TokenSignService;
import hou.tidaa.user.domain.dto.UsernamePasswordDto;
import hou.tidaa.user.domain.ports.UserAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.AuthenticationException;
import javax.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/tokens", produces = APPLICATION_JSON_VALUE)
public class JWTResource {
    private final UserAuthentication userAuthentication;
    private final TokenSignService<String> tokenSignService;

    @Autowired
    public JWTResource(final UserAuthentication userAuthentication, final TokenSignService<String> tokenSignService) {
        this.userAuthentication = userAuthentication;
        this.tokenSignService = tokenSignService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Token<String>> login(@Valid @RequestBody final UsernamePasswordDto dto)
            throws AuthenticationException {
        final IdentityDto auth = userAuthentication.authentication(dto)
                .orElseThrow(() -> new AuthenticationException("auth failed"));

        final Token<String> token = tokenSignService.sign(auth);
        return ResponseEntity.ok(token);
    }
}
