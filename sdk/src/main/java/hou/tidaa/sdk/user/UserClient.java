package hou.tidaa.sdk.user;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public interface UserClient {
    @RequestMapping(method = RequestMethod.GET, value = "/api/users/{id}/name")
    String getUsername(@PathVariable("id") final String id);
}
