package hou.tidaa.user.adapter.rest;

import hou.tidaa.user.domain.dto.UserRegisterDto;
import hou.tidaa.user.domain.ports.UserManagement;
import hou.tidaa.user.domain.ports.UserRepository;
import hou.tidaa.user.domain.model.User;
import org.hibernate.QueryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/users", produces = APPLICATION_JSON_VALUE)
public class UserResource {
    private final UserManagement userManagement;
    private final UserRepository userRepository;

    @Autowired
    public UserResource(final UserManagement userManagement, final UserRepository userRepository) {
        this.userManagement = userManagement;
        this.userRepository = userRepository;
    }

    @GetMapping("/{id}")
    public User findUserById(@PathVariable final String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new QueryException("No user found with uid:" + id));
    }

    @GetMapping("/{id}/name")
    public String findUsernameById(@PathVariable final String id) {
        return findUserById(id).getName();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@Valid @RequestBody final UserRegisterDto dto) {
        return userManagement.register(dto);
    }
}
