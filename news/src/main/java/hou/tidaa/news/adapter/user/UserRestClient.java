package hou.tidaa.news.adapter.user;

import hou.tidaa.sdk.user.UserClient;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "user", fallback = UserRestClientFallback.class)
public interface UserRestClient extends UserClient {
}
