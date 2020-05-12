package hou.tidaa.news.adapter.user;

import hou.tidaa.sdk.user.UserClientFallback;
import org.springframework.stereotype.Component;

@Component
public class UserRestClientFallback extends UserClientFallback implements UserRestClient {
}
