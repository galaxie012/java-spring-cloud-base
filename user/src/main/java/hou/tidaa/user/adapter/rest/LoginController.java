package hou.tidaa.user.adapter.rest;

import hou.tidaa.user.domain.dto.LoginResponse;
import hou.tidaa.user.domain.dto.UserLoginDto;
import hou.tidaa.user.service.LoginService;
import hou.tidaa.user.service.UserLoginException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/auth/login", produces = APPLICATION_JSON_VALUE)
public class LoginController {
    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping
    public LoginResponse login(@Valid @RequestBody UserLoginDto dto) throws UserLoginException {
        return loginService.login(dto);
    }
}
