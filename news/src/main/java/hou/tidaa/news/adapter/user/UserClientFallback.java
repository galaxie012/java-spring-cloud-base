package hou.tidaa.news.adapter.user;

import org.springframework.stereotype.Component;

@Component
public class UserClientFallback implements UserClient {
    @Override
    public String getUsername(String id) {
        return "Anonymity";
    }
}
