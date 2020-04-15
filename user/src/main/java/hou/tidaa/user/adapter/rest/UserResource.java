package hou.tidaa.user.adapter.rest;

import hou.tidaa.user.service.UserNotFoundException;
import hou.tidaa.user.domain.dto.UserRegisteringDto;
import hou.tidaa.user.domain.model.User;
import hou.tidaa.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/users", produces = APPLICATION_JSON_VALUE)
public class UserResource {
    private final UserService userService;

    @Autowired
    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public User get(@PathVariable String id) throws UserNotFoundException {
        return userService.findById(id);
    }

    @GetMapping("/{id}/name")
    public String getName(@PathVariable String id) throws UserNotFoundException {
        return userService.findById(id).getName();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@Valid @RequestBody UserRegisteringDto dto) {
        return userService.saveUser(dto);
    }
}
