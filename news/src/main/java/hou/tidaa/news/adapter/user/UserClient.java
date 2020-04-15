package hou.tidaa.news.adapter.user;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "user", fallback = UserClientFallback.class)
public interface UserClient {
    @RequestMapping(method = RequestMethod.GET, value = "/{id}/name")
    String getUsername(@PathVariable("id") String id);
}
